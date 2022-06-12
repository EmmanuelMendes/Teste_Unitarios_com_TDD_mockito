package br.com.entidades;

public class Usuario {
	private String name;

	public Usuario(String name) {
		super();
		this.name = name;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name==null)? 0 : name.hashCode());
		return result;
		
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(getClass() != obj.getClass())return false;
		Usuario outro = (Usuario) obj;
		if(name == null) {
			if(outro.name != null) return false; 
		}else if(!name.equals(outro.name)) return false;
		return true;
	}
	

}
