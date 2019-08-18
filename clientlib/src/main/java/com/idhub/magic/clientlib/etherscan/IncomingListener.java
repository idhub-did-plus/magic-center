package com.idhub.magic.clientlib.etherscan;

import java.util.List;

public interface IncomingListener<T> {
	void income(List<T> ts);
}
