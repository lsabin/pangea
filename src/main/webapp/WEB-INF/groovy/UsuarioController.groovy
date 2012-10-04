import com.googlecode.objectify.ObjectifyService
import com.pangea.Util
import com.pangea.util.Codificador
import com.model.Usuario


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
		        u.claveActivacion = activacion
		        u.store()

		        log.info "usuario creado............"
				
				

				
				request.mensaxe="Usuario $usuario creado."
				//request.notas="Recibirás un correo en tu cuenta ${correo} para activar tu usuario."	

				mensajeActivacion = "${i18n.mensajeActivacion(correo)}"
				request.notas = mensajeActivacion



				forward '/WEB-INF/pages/confirmacion.gtpl'	
			} else {
				request.erro = "El usuario $usuario ya existe!"
				forward '/WEB-INF/pages/registro.gtpl'				
			}
			
		} catch (Exception e) {
			request.erro = "Se ha producido un error que no estaba previsto!" 
			log.severe "Error:  ${e.getMessage()}"
			forward "/WEB-INF/pages/registro.gtpl"	
		}


		//envia un email de confirmacion
		enviaCorreo(correo,activacion, nombre)
    
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


login = {

	if (usuario && contrasinal) {

		usuarioEncontrado = buscaUsuario(usuario) 



		if (usuarioEncontrado) {

			log.info "Encontrado el usuario $usuario"


			if (usuarioEncontrado.activo) {

				def sesion = request.getSession(true)
				
				String contrasinalCodificado = Codificador.codifica(contrasinal)
				
				if (contrasinalCodificado.equals(usuarioEncontrado.contrasinal)) {
					sesion.setAttribute('usuario',usuarioEncontrado.usuario)
					request.mensaxe = "Benvido, ${usuarioEncontrado.nombre}"

					forward '/WEB-INF/pages/index.gtpl'	

				} else {
					request.erro ="La clave no es valida"
					forward '/WEB-INF/pages/login.gtpl'	
				}


			} else {

				log.info "El usuario $usuario no fue activado."

				request.erro = "El usuario $usuario no fue activado."
				forward '/WEB-INF/pages/login.gtpl'				

			}


		} else {
			log.info "NO existe el usuario $usuario"	
			request.erro="Non existe ese usuario!"
			forward '/WEB-INF/pages/login.gtpl'				
		}


	} else {
		request.erro="Tes que indicar o teu usuario e contrasinal!"
		forward '/WEB-INF/pages/login.gtpl'	
	}

}


activa = {

	if (params['key']) {
		
		def activacion = params['key']
		
		Usuario usuarioParaActivar = buscaUsuarioPorActivacion(activacion)
		
		if (usuarioParaActivar) {
			
			usuarioParaActivar.activo = true
			usuarioParaActivar.store()

			request.mensaxe = "Usuario ${usuarioParaActivar.usuario} activado."
			request.contido = """Ya puedes hacer login <a href='/login'>aqui</a>"""	
			forward '/WEB-INF/pages/exito.gtpl'	
		} else {
			request.erro = "Non foi atopado ningun usuario con esa chave de activacion!"
			forward '/WEB-INF/pages/index.gtpl'
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


Usuario buscaUsuario(String nombreUsuario) {

	def usuarios = Usuario.search(filter:["usuario ==": nombreUsuario])

	if (usuarios) {
		return usuarios[0]
	} else {
		return null
	}
	
}


Usuario buscaUsuarioPorActivacion(String claveActivacion) {

	def usuarios = Usuario.search(filter:["claveActivacion ==": claveActivacion])

	if (usuarios) {
		return usuarios[0]
	} else {
		return null
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