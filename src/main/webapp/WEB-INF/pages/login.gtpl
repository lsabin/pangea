<% include '/WEB-INF/includes/header-nueva.gtpl' %>

<%
def erro = request.getAttribute('erro') 
def mensaxeExito = request.getAttribute('mensaxe')

def notas = request.getAttribute('notas')
%>

<!--
<div class="hero-unit center">
  <p>
  </p>
  
</div>
-->

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
<form id="formulario" class="form-horizontal" method="POST" action="/usuarios/login">

  <fieldset>
    <legend></legend>

    <div class="control-group">
      <label class="control-label" for="usuario">Nombre usuario</label> 
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
      <div class="controls">
          <input id="submit" class="btn btn-primary" name="submit" type="submit" value="Login"/>
      </div>
    </div>        


  </fieldset>
</form>	
	</div>
</div>




<% include '/WEB-INF/includes/footer.gtpl' %>

