package com.idhub.magic.clientlib.interfaces;

import com.idhub.magic.center.ustorage.entity.IdentityArchive;

public interface ArchiveStorage {
	void storeArchive(IdentityArchive archive);
}
