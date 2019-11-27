package com.example.zyjdahuidatadownloads.bean;

import com.google.gson.annotations.Expose;

public class DaHuiDataFiles {

	@Expose
	public String dataUploadName;
	@Expose
	public String dataUploadUrl;
	@Expose
	public String fileFormat;

	@Override
	public String toString() {
		return "DaHuiDataFiles [dataUploadName=" + dataUploadName
				+ ", dataUploadUrl=" + dataUploadUrl + ", fileFormat="
				+ fileFormat + "]";
	}

}
