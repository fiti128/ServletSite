package ru.retbansk.jdbc.ivanTest;

public enum Role {
	GUEST("guest"), USER("user"), ADMIN("admin");
	private String roleName;
	private static Role[] realValues;
	
	private Role(String roleName) {
		this.roleName = roleName;
		
	}
	// Для удобства добавим следующие методы
	public String getName() {
		return roleName;
	}
	
	// Метод lookUp(String name), для поиска роли по имени
	public static Role lookUp(String name) {
		if (name == null) {
			return null;
		}
		for (int i = 0; i < values().length; i++) {
			if (values()[i].getName().equals(name)) {
				return values()[i];
			}
		}
		return null;
	}
	
	// Метод realValues(), который возвращает массив авторизованных ролей:
	public static Role[] realValues() {
		if (realValues == null) {
			realValues = new Role[values().length-1];
			for (int i = 0, j  = 0; i < values().length; i++) {
				if (values()[i] != GUEST) {
					realValues[j++] = values()[i];
					
				}
			}
		}
		return realValues;
	}
}
