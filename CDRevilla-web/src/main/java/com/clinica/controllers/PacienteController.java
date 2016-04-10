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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named("pacienteController")
@SessionScoped
public class PacienteController implements Serializable {

  private static final long serialVersionUID = -2564031884483676327L;

  @EJB
  private com.clinica.fachadas.PacienteFacadeLocal ejbFacade;
  private List<Paciente> items = null;
  private Paciente selected;
  private UploadedFile file;
  private StreamedContent image;
  private String pathImage;
  private String contentType;
  final static org.apache.log4j.Logger logger = Log4jConfig.getLogger(PacienteController.class.getName());

  public PacienteController() {
  }

  public Paciente getSelected() {
    return selected;
  }

  @PostConstruct
  void init() {
    prepareCreate();
    String projectStage = FacesContext.getCurrentInstance().getApplication().getProjectStage().toString();
    if(projectStage.equals("Production")){
      pathImage = ResourceBundle.getBundle("/deploy").getString("productionPacientePhotoPath");
    } else if (projectStage.equals("Development")){
      pathImage = ResourceBundle.getBundle("/deploy").getString("developmentPacientePhotoPath");
    }
    logger.info("Project stage : " + projectStage);

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
    selected.setLugarNacimiento("");
    selected.setRaza("");
    selected.setGradoInstruccion("");
    selected.setGradoInstruccion("");
    selected.setRaza("");
    selected.setOcupación("");
    selected.setReligion("");
    selected.setEstadoCivil("");
    selected.setFoto("");
    initializeEmbeddableKey();
    return selected;
  }

  public void create() throws FileNotFoundException {

    if (selected.getIdPaciente() == null) {//grabar
      selected.setFechaApertura(new Date());
      persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PacienteCreated"));
      if(file != null){ //no se guarda la foto
        uploadFoto();
        logger.info("Save file = null");
      }
      if (!JsfUtil.isValidationFailed()) {
        items = null;    // Invalidate list of items to trigger re-query.
      }
      RequestContext.getCurrentInstance().reset("PacienteCreateEditForm:PacienteFields");
      //CLEAN
      selected = new Paciente();
      image = new DefaultStreamedContent(null);
      file = null;
    } else { //Editar
      if(file != null){
        uploadFoto();
        logger.info("Update file = null");
      }
      update();
      //load photo
      FileInputStream stream = new FileInputStream(pathImage + selected.getIdPaciente() + ".jpg");
      image = new DefaultStreamedContent(stream, "image/jpg");
      logger.info("Upload photo OK");
      
    }
  }

  public void update() {
    persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PacienteUpdated"));
  }

  public void destroy(Integer idPaciente) throws FileNotFoundException {
    destroy();
    prepareCreate();
    image = new DefaultStreamedContent(null);
    file = null;
    RequestContext.getCurrentInstance().reset("PacienteCreateEditForm:PacienteFields");
  }

  public void destroy() {
    persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PacienteDeleted"));
    if (!JsfUtil.isValidationFailed()) {
      selected = null; // Remove selection
      items = null;    // Invalidate list of items to trigger re-query.
    }
  }

  public List<Paciente> getItems() {
//    if (items == null) {
    items = getFacade().findAll();
//    }
    return items;
  }

  private void persist(PersistAction persistAction, String successMessage) {
    if (selected != null) {
      setEmbeddableKeys();
      try {
        if (persistAction == PersistAction.UPDATE) {
          getFacade().edit(selected);
        } else if(persistAction == PersistAction.CREATE){
          Integer id = getFacade().persist(selected);
          selected.setIdPaciente(id);
        }else {
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

  public void loadPacienteSelected(Integer idPaciente) throws FileNotFoundException {
    selected = ejbFacade.find(idPaciente);
//    file = FileInputStream(new File("/path/to/images", filename));
    logger.info("Error stream");
    FileInputStream stream = new FileInputStream(pathImage + selected.getIdPaciente() + ".jpg");
    logger.info("Error load image");
    image = new DefaultStreamedContent(stream, "image/jpg");
  }

  public void loadImage() throws IOException {
//    image = new DefaultStreamedContent(new ByteArrayInputStream(file.getContents()), "image/jpg");
//    image = null;
//    image = new DefaultStreamedContent(file.getInputstream(), "image/jpg");
    
//    image = new DefaultStreamedContent(photo.getInputStream(), "image/jpg");
    
    logger.info("Exito load image!");
  }

  public void clean() throws IOException {
    RequestContext.getCurrentInstance().reset("PacienteCreateEditForm:PacienteFields");
    prepareCreate();
    image = new DefaultStreamedContent(null);
    file = null;
    logger.info("Clean OK");
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
  }

  public void uploadFoto() {

    try {

//      String fileName = file.getFileName();
      String fileName = selected.getIdPaciente() + ".jpg";
      if(selected.getIdPaciente()==null) //Si se va a crear
        image = new DefaultStreamedContent(new ByteArrayInputStream(file.getContents()), "image/png");
//      file.get
      InputStream in = file.getInputstream();
      String destination = pathImage;

      // write the inputStream to a FileOutputStream
      OutputStream out = new FileOutputStream(new File(destination + fileName));

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

  

  public UploadedFile getFile() {
    return file;
  }

  public void setFile(UploadedFile file) {
    this.file = file;
  }

  public StreamedContent getImage() throws FileNotFoundException {
    if(selected.getIdPaciente() != null){
      FileInputStream stream = new FileInputStream(pathImage + selected.getIdPaciente() + ".jpg");
      image = new DefaultStreamedContent(stream, "image/jpg");
  }
    return image;
  }

  public void setImage(StreamedContent image) {
    this.image = image;
  }

  public Paciente getPaciente(java.lang.Integer id) {
    return getFacade().find(id);
  }

  public List<Paciente> getItemsAvailableSelectMany() {
    return getFacade().findAll();
  }

  public Map<Integer, Paciente> getItemsAvailableSelectOne() {

    Map<Integer, Paciente> combo = new HashMap<>();

    List<Paciente> list = getFacade().findAll();
    for (Paciente item : list) {
      combo.put(item.getIdPaciente(), item);
    }
    return combo;
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
