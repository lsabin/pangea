package com.model

import groovyx.gaelyk.obgaektify.ObgaektifiableLongId
import com.googlecode.objectify.Key
import com.googlecode.objectify.annotation.NotSaved

class Usuario extends ObgaektifiableLongId implements Serializable
{
	String nombre
	String usuario
	String contrasinal
	String correo
	Boolean activo = false
	String claveActivacion
}