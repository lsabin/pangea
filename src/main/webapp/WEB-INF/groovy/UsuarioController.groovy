import com.model.Usuario
import com.googlecode.objectify.ObjectifyService

def nombre = params.nombre

def action = params.action ?: "list"

log.info "action: $action"
log.info "nombre: $nombre"

create = {
    if (nombre) {
        def usuario = new Usuario()
        usuario.nombre = nombre
        usuario.store()
        
        request.mensaxe="usuario creado."
    }
    
    //list()
  	forward '/WEB-INF/pages/registro.gtpl'

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