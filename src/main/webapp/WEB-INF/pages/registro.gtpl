<% include '/WEB-INF/includes/header.gtpl' %>

<%
def erro = request.getAttribute('erro') 
def mensaxeExito = request.getAttribute('mensaxe')

def notas = request.getAttribute('notas')
%>

<div class="hero-unit center">
  <p>
  Registra tu usuario
  </p>
  
</div>

<% if (erro) { %>
<div class="alert alert-error">
${erro?:''}
</div>
<% } %>



<% if (mensaxeExito) { %>
<div class="alert alert-success">
${mensaxeExito?:''}
</div>
<% } %>


<% if (notas) { %>
<div class="alert alert-success">
${notas?:''}
</div>
<% } %>





<div class="row">
	<div class="span12">
<form id="formulario" class="form-horizontal" method="POST" action="/usuarios/create">

  <fieldset>
    <legend></legend>
    <div class="control-group">
      
      <label class="control-label" for="nombre">Nombre</label> 
      <div class="controls">
        <input type="text" class="input-xlarge" id="nombre" name="nombre">
      </div>
    </div>


    <div class="control-group">
      
      <label class="control-label" for="usuario">${i18n['nombre.usuario']}</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="usuario" name="usuario">
        
      </div>
    </div>


    <div class="control-group">
      
      <label class="control-label" for="contrasinal">${i18n['clave']}</label> 
      <div class="controls">
        <input type="password" class="input-xlarge" id="contrasinal" name="contrasinal">
       </div>
    </div>

    <div class="control-group">
      
      <label class="control-label" for="confirmaclave">${i18n['confirma.clave']}</label>
      <div class="controls">
        <input type="password" class="input-xlarge" id="confirmaclave" name="confirmaclave">
       </div>
    </div>


    <div class="control-group">
      
      <label class="control-label" for="correo">Correo</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="correo" name="correo">
      </div>
    </div>        

    <div class="control-group">
      <div class="controls">
          <input id="submit" class="btn btn-primary" name="submit" type="submit" value="Crea usuario"/>
      </div>
    </div>        


  </fieldset>
</form>	
	</div>
</div>




<% include '/WEB-INF/includes/footer-validacion.gtpl' %>

