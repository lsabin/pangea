<% include '/WEB-INF/includes/header.gtpl' %>

<%
def erro = request.getAttribute('erro') 
def mensaxeExito = request.getAttribute('mensaxe')
%>


<% if (erro) { %>
<div class="erro">
${erro?:''}
</div>
<% } %>



<% if (mensaxeExito) { %>
<div class="alert alert-success">
${mensaxeExito?:''}
</div>
<% } %>


<div class="hero-unit center">
	<p>
	Registra tu usuario
	</p>
	
</div>

<div class="row">
	<div class="span12">
<form class="form-horizontal" method="POST" action="/usuarios/create">

  <fieldset>
    <legend>Legend text</legend>
    <div class="control-group">
      <label class="control-label" for="nombre">Nombre</label>
      <div class="controls">
        <input type="text" class="input-xlarge" id="nombre" name="nombre">
        <p class="help-block">Supporting help text</p>
            <input id="submit" class="btn btn-primary" name="submit" type="submit" 
          value="Crea usuario"/>
        
      </div>
    </div>
  </fieldset>
</form>	
	</div>
</div>




<% include '/WEB-INF/includes/footer.gtpl' %>

