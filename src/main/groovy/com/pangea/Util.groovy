package com.pangea;

import com.google.appengine.api.datastore.*
import static com.google.appengine.api.datastore.FetchOptions.Builder.*
import org.apache.commons.codec.binary.Hex
import java.io.UnsupportedEncodingException
import java.security.*


class Util {
    
    public String xeraCadeaAleatoria(int lonxitude) {
        String seeds = "abcdefghijklmnopqrstuvwxyz012346789"
            
        def cadena = ""
        int count = seeds.length()
        
        Random generator = new Random()
        
        for (int i=0;i<lonxitude;i++){
            def indice = generator.nextInt(count-1)
            cadena = cadena + seeds[indice]
        }
        
        return cadena
      
    }
    
    def obtenUsuarioSesion(String usuarioSession) {
    	
    	def query = new Query("usuario")
    	query.addFilter("nome", Query.FilterOperator.EQUAL, usuarioSession)
     
    	PreparedQuery preparedQuery = datastore.prepare(query)
     
    	// return only the first 10 results
    	def entities = preparedQuery.asList( withLimit(10) )
    	
    	if (entities) {
    		return entities[0]
    	} else {
    		return null
    	}
    	
    }   


     String codifica(String clave) throws NoSuchAlgorithmException , UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance(algoritmo)
        digest.update(clave.getBytes(codificacion))
        byte[] raw = digest.digest()
        return new String(Hex.encodeHex(raw))
    
    }
        
}