
get "/", forward: "/WEB-INF/pages/index.gtpl"
get "/datetime", forward: "/datetime.groovy"

get "/registro", forward: "/WEB-INF/pages/registro.gtpl"

get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"
all "/usuarios/@action", forward: "/UsuarioController.groovy?action=@action"

