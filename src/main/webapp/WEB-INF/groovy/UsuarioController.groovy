import com.model.Usuario
import com.googlecode.objectify.ObjectifyService
import com.pangea.Util
import com.pangea.util.Codificador

def usuario = params.usuario
def contrasinal = params.contrasinal
String correo = params.correo
String nombre = params.nombre

def action = params.action ?: "list"

log.info "action: $action"
log.info "nombre: $nombre"
log.info "contrasinal:  $contrasinal"
log.info "correo: $correo"
log.info "usuario: $usuario"


create = {

    if (usuario && contrasinal && correo && nombre) {
    
		//xera una palabra de rexistro
		Util util = new Util()
		String activacion = util.xeraCadeaAleatoria(32)

		log.info "cadena activacion: $activacion"
		
		try {
			
			if (!usuarioExistente(usuario)) {

				log.info "no existe el usuario......"
				//Codifica o contrasinal
				String contrasinalCodificado = Codificador.codifica(contrasinal)

				log.info "constrasinal codificado ${contrasinalCodificado}"
				
				//crea o usuario na db
		        def u = new Usuario()
		        u.nombre = nombre
		        u.correo = correo
		        u.contrasinal = contrasinalCodificado
		        u.usuario = usuario
		        u.store()

		        log.info "usuario creado............"
				
				
				//envia un email de confirmacion
				enviaCorreo(correo,activacion, nombre)
				
				request.mensaxe="Usuario $usuario creado."
				request.notas="Recibirás un correo en tu cuenta ${correo} para activar tu usuario."	
				forward '/WEB-INF/pages/confirmacion.gtpl'	
			} else {
				request.erro = "El usuario $usuario ya existe!"
				forward '/WEB-INF/pages/registro.gtpl'				
			}
			
		} catch (Exception e) {
			request.erro = "Hemos tenido un error que no estaba previsto!" 
			log.error "Error:  ${e.getMessage()}"
			forward "/WEB-INF/pages/registro.gtpl"	
		}


    
    }
    

}

list = {
    def usuarios = Usuario.search(filter:[:])


    request.usuarios = usuarios
}

listado = {

    def usuarios = Usuario.search(filter:[:])
    
    html.select(id:'actividad', name:'actividad') {
        usuarios.eachWithIndex {tipo,i->
            if (tipo) {
                option value:"${tipo.key?.id}" ,"${tipo.nombre.toString()} - ${tipo.usuario}"
            }
        }
    
    }


}



this."$action".call()


boolean usuarioExistente(String nombreUsuario) {

	def usuarios = Usuario.search(filter:["usuario ==": nombreUsuario])

	if (usuarios) {
		return true 
	} else {
		return false	
	}
	
}


def enviaCorreo(String correo, def activacion, String nomeCompleto) {
	
	
	mail.send sender: "info.ospobos@gmail.com",
	to: correo,
	subject: "Confirmacion de registro en pangea",
	textBody: """
		Hola ${nomeCompleto},
		
		Tienes que activar tu nueva cuenta en el siguiente enlace:
		
		http://ospobos.appspot.com/activa/$activacion
		
		Saludos
		"""
	
}