package net.apartium.servers.infernumpvp.utils;

public class ChatBuilder {

	public static String build(String... args) {
		StringBuilder sb = new StringBuilder();
		boolean arg = false;
		for (String str : args) {
			if (arg) {
				sb.append(" §6" + str);
				arg = false;
			} else {
				sb.append(" §7" + str);
				arg = true;

			}
		}
		return sb.toString();
	}
}
