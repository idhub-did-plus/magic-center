package com.idhub.magic.did.driver;

import java.util.Map;

import com.idhub.magic.did.resolver.ResolutionException;
import com.idhub.magic.did.resolver.ResolveResult;

public interface Driver {

	public static final String PROPERTIES_MIME_TYPE = "application/json";

	public ResolveResult resolve(String identifier) throws ResolutionException;
	public Map<String, Object> properties() throws ResolutionException;
}
