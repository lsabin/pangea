get "/favicon.ico", redirect: "/images/gaelyk-small-favicon.png"
get "/", forward: "/WEB-INF/pages/index.gtpl"
get "/datetime", forward: "/datetime.groovy"

get "/registro", forward: "/WEB-INF/pages/registro.gtpl"
get "/login", forward: "/WEB-INF/pages/login.gtpl"


get "/activa/@key", forward: "/UsuarioController.groovy?action=activa&key=@key"
all "/usuarios/@action", forward: "/UsuarioController.groovy?action=@action"




