$(document).ready(function () {

  function readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function (e) {
        //$('#j_idt85:blah').attr('src', e.target.result);
//        $('#formId\\:blah').attr('src', e.target.result);
        $('.MostrarImagen')
                .attr('src', e.target.result)
      }

      reader.readAsDataURL(input.files[0]);
    }
  }
  
  $("#PacienteCreateEditForm\\:CargarImagen_input").change(function () {
    readURL(this);
  });
  
  function readPicture(input)
  {
    if (input.files && input.files[0])
    {
      var reader = new FileReader();
      reader.onload = function (e)
      {
        $('.MostrarImagen')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
      };
    }
    reader.readAsDataURL(input.files[0]);
  }
  
//  $("#PacienteCreateForm\\:CargarImagen_input").change(function () {
//    readPicture(this);
//  });
  
//  $("input:regex(id, .*CargarImagen_input.*)").css('background-color','yellow');
  
  
  
  
  
  
  PrimeFaces.locales['es'] = {
    closeText: 'Cerrar',
    prevText: 'Anterior',
    nextText: 'Siguiente',
    monthNames: ['Enero','Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
    monthNamesShort: ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun','Jul','Ago','Sep','Oct','Nov','Dic'],
    dayNames: ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'],
    dayNamesShort: ['Dom','Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab'],
    dayNamesMin: ['D','L','M','X','J','V','S'],
    weekHeader: 'Semana',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Sólo hora',
    timeText: 'Tiempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    currentText: 'Fecha actual',
    ampm: false,
    month: 'Mes',
    week: 'Semana',
    day: 'Día',
    allDayText : 'Todo el día'
};

});


  