package com.elo7.marte.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class Converter {
	private static final ModelMapper modelMapper = new ModelMapper();
	
	private Converter() {}
	
	public static <S, D> D convert(S source, Class<D> clazz) {
	    return (source != null) ? modelMapper.map(source, clazz) : null;
	}
	
	public static <S, D> D convert(S source, Class<D> clazz, PropertyMap<S,D> property) {
	    ModelMapper modelMapper2 = new ModelMapper();
	    if(source == null) {
	        return null;
	    } else {
	        if(property != null) {
	            return modelMapper2.addMappings(property).map(source);
	        } else {
	            return convert(source, clazz);
	        }
	    }
	}
	
	public static <S, D> List<D> convert(Collection<S> sourceCollection, Class<D> clazz, PropertyMap<S,D> property) {
	    List<D> result = new LinkedList<D>();
	
	    if(sourceCollection == null) return null;
	    for (S source : sourceCollection) {
	        result.add(convert(source, clazz, property));
	    }
	    return result;
	}
}

