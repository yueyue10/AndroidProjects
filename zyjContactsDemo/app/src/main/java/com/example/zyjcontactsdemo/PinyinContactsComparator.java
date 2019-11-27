package com.example.zyjcontactsdemo;

import java.util.Comparator;

public class PinyinContactsComparator implements Comparator<ContactBean> {

	@Override
	public int compare(ContactBean bean1, ContactBean bean2) {

		String tmp1 = FirstLetterUtil.getFirstLetter(bean1.name);
		String tmp2 = FirstLetterUtil.getFirstLetter(bean2.name);

		if (tmp1.equals("@") || tmp2.equals("#")) {
			return -1;
		} else if (tmp1.equals("#") || tmp2.equals("@")) {
			return 1;
		} else {
			return tmp1.compareTo(tmp2);
		}
	}

}
