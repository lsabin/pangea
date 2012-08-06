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
		        u.store()

		        log.info "usuario creado............"
				
				
				//envia un email de confirmacion
				enviaCorreo(correo,activacion, nombre)
				
				request.mensaxe="Usuario $usuario creado."
				request.notas="Recibirás un correo dirixido a ${correo} para activar o teu usuario."	
				forward '/WEB-INF/pages/registro.gtpl'	
			} else {
				request.erro = "O usuario $usuario xa existe!"
				forward '/WEB-INF/pages/registro.gtpl'				
			}
			
		} catch (Exception e) {
			request.erro = "Produciuse un erro inesperado!"
			forward "/erro.gtpl"	
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
                option value:"${tipo.key?.id}" ,"${tipo.nombre.toString()}"
            }
        }
    
    }


}



this."$action".call()


boolean usuarioExistente(String nombreUsuario) {
	return false
}


def enviaCorreo(String correo, def activacion, String nomeCompleto) {
	
	
	mail.send sender: "info.ospobos@gmail.com",
	to: correo,
	subject: "Confirmacion de rexistro",
	textBody: """
		Hola ${nomeCompleto},
		
		Tes que activar a tua conta na seguinte ligazon:
		
		http://ospobos.appspot.com/activa/$activacion
		
		Saudos
		"""
	
}