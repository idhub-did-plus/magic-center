package com.idhub.magic.clientlib.interfaces;

import com.idhub.magic.center.ustorage.entity.IdentityArchive;

public interface IdentityStorage {
	void storeArchive(IdentityArchive archive);
}
