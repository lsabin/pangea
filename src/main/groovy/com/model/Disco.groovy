package com.model

import groovyx.gaelyk.obgaektify.ObgaektifiableLongId
import com.googlecode.objectify.Key
import com.googlecode.objectify.annotation.NotSaved

class Disco extends ObgaektifiableLongId implements Serializable
{
	String artista
	String titulo
	String contenedor
	Boolean escuchar = false
}