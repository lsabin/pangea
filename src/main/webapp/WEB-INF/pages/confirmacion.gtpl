<% include '/WEB-INF/includes/header.gtpl' %>


<%
def erro = request.getAttribute('erro') 
def mensaxeExito = request.getAttribute('mensaxe')

def notas = request.getAttribute('notas')
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


<% if (notas) { %>
<div class="alert alert-success">
${notas?:''}
</div>
<% } %>



<% include '/WEB-INF/includes/footer.gtpl' %>

