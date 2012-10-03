// 
//	jQuery Validate example script
//
//	Prepared by David Cochran
//	
//	Free for your use -- No warranties, no guarantees!
//

$(document).ready(function(){

	// Validate
	// http://bassistance.de/jquery-plugins/jquery-plugin-validation/
	// http://docs.jquery.com/Plugins/Validation/
	// http://docs.jquery.com/Plugins/Validation/validate#toptions
	
		$('#formulario').validate({
	    rules: {
	      nombre: {
	        minlength: 2,
	        required: true
	      },
	      correo: {
	        required: true,
	        email: true
	      },
	      usuario: {
	      	minlength: 4,
	        required: true
	      },
	      contrasinal: {
	        minlength: 4,
	        required: true
	      }

	      confirmaclave: {
	      	required: true,
	      	equalTo:"#contrasinal"
	      }
	    },

		messages: {
			usuario: "Este campo es obligatorio",
			nombre: "Este campo es obligatorio",
			contrasinal: "Este campo es obligatorio",
			correo: {
                required:"Este campo es obligatorioo",
                email:"No es un correo válido"
            },
            confirmaclave: {
                required:"Este campo es obligatorioo",
                equalTo:"No coincide las contraseñas"
            }
        },


	    highlight: function(label) {
	    	$(label).closest('.control-group').removeClass('success').addClass('error');
	    },
	    success: function(label) {
	    	label
	    		.text('OK!').addClass('valid')
	    		.closest('.control-group').addClass('success');
	    }
	    
	  });
	  
}); // end document.ready