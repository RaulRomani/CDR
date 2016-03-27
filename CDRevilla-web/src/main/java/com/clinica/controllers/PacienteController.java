package com.clinica.controllers;

import com.clinica.entidades.Paciente;
import com.clinica.controllers.util.JsfUtil;
import com.clinica.controllers.util.JsfUtil.PersistAction;
import com.clinica.controllers.util.Log4jConfig;
import com.clinica.fachadas.PacienteFacadeLocal;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named("pacienteController")
@ViewScoped
public class PacienteController implements Serializable {

  private static final long serialVersionUID = -2564031884483676327L;

  @EJB
  private com.clinica.fachadas.PacienteFacadeLocal ejbFacade;
  private List<Paciente> items = null;
  private Paciente selected;
  private UploadedFile file;
  private StreamedContent image;
  final static org.apache.log4j.Logger logger = Log4jConfig.getLogger(PacienteController.class.getName());

  public PacienteController() {
  }

  public Paciente getSelected() {
    return selected;
  }

  @PostConstruct
  void init() {

  }

  public void setSelected(Paciente selected) {
    this.selected = selected;
  }

  protected void setEmbeddableKeys() {
  }

  protected void initializeEmbeddableKey() {
  }

  private PacienteFacadeLocal getFacade() {
    return ejbFacade;
  }

  public Paciente prepareCreate() {
    selected = new Paciente();
    initializeEmbeddableKey();
    return selected;
  }

  public void create() {

    uploadFoto();
    persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PacienteCreated"));
    if (!JsfUtil.isValidationFailed()) {
      items = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public void update() {
    persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PacienteUpdated"));
  }

  public void destroy() {
    persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PacienteDeleted"));
    if (!JsfUtil.isValidationFailed()) {
      selected = null; // Remove selection
      items = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public List<Paciente> getItems() {
    if (items == null) {
      items = getFacade().findAll();
    }
    return items;
  }

  private void persist(PersistAction persistAction, String successMessage) {
    if (selected != null) {
      setEmbeddableKeys();
      try {
        if (persistAction != PersistAction.DELETE) {
          getFacade().edit(selected);
        } else {
          getFacade().remove(selected);
        }
        JsfUtil.addSuccessMessage(successMessage);
      } catch (EJBException ex) {
        String msg = "";
        Throwable cause = ex.getCause();
        if (cause != null) {
          msg = cause.getLocalizedMessage();
        }
        if (msg.length() > 0) {
          JsfUtil.addErrorMessage(msg);
        } else {
          JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
      } catch (Exception ex) {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
      }
    }
  }

  public void uploadFoto() {

    try {

      String fileName = file.getFileName();

      StreamedContent listImage = new DefaultStreamedContent(new ByteArrayInputStream(file.getContents()), "image/png");
//      file.get
      InputStream in = file.getInputstream();
      String destination = "D:\\tmp\\";

      // write the inputStream to a FileOutputStream
      OutputStream out = new FileOutputStream(new File(destination + fileName));

      selected.setFoto(destination + selected.getDni());

      int read = 0;
      byte[] bytes = new byte[1024];

      while ((read = in.read(bytes)) != -1) {
        out.write(bytes, 0, read);
      }

      in.close();
      out.flush();
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public StreamedContent getImage() {
    if (image == null) {
      try {
        String imageDefaulf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/fotos/foto.jpg");
        image = new DefaultStreamedContent(new FileInputStream(imageDefaulf), "image/png"); // load a dummy image
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    logger.info("IMAGEN CARGADA OK");
    return image;
  }

  public void handleFileUpload(FileUploadEvent event) {
    final UploadedFile uploadedFile = event.getFile();

    image = new DefaultStreamedContent(new ByteArrayInputStream(uploadedFile.getContents()), "image/png");

    logger.info("Upload file OK:");
  }

  public UploadedFile getFile() {
    return file;
  }

  public void setFile(UploadedFile file) {
    this.file = file;
  }

  public Paciente getPaciente(java.lang.Integer id) {
    return getFacade().find(id);
  }

  public List<Paciente> getItemsAvailableSelectMany() {
    return getFacade().findAll();
  }

  public List<Paciente> getItemsAvailableSelectOne() {
    return getFacade().findAll();
  }

  @FacesConverter(forClass = Paciente.class)
  public static class PacienteControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
      if (value == null || value.length() == 0) {
        return null;
      }
      PacienteController controller = (PacienteController) facesContext.getApplication().getELResolver().
              getValue(facesContext.getELContext(), null, "pacienteController");
      return controller.getPaciente(getKey(value));
    }

    java.lang.Integer getKey(String value) {
      java.lang.Integer key;
      key = Integer.valueOf(value);
      return key;
    }

    String getStringKey(java.lang.Integer value) {
      StringBuilder sb = new StringBuilder();
      sb.append(value);
      return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
      if (object == null) {
        return null;
      }
      if (object instanceof Paciente) {
        Paciente o = (Paciente) object;
        return getStringKey(o.getIdPaciente());
      } else {
        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Paciente.class.getName()});
        return null;
      }
    }

  }

}
