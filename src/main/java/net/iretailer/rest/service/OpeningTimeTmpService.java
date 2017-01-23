package net.iretailer.rest.service;

import net.iretailer.rest.model.OpeningTimeTmp;
import net.iretailer.rest.model.OpeningTimeTmpKey;

public interface OpeningTimeTmpService {
	OpeningTimeTmp getOpeningTimeByDate(OpeningTimeTmpKey key);
}
