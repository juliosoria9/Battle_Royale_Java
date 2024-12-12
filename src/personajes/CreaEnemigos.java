package personajes;

public class CreaEnemigos{

	public Enemigo crearEnemigo(String dificultad)
	{
		if(dificultad == "facil")
		{
			return crearEnemigoFacil();
		}else if(dificultad == "media")
		{
			return crearEnemigoMedio();
		}
		
		return crearEnemigoDificil();
	}
	
	//falta pasarles arma
	private Enemigo crearEnemigoFacil()
	{
		return new Enemigo (10, Enemigo.setArma(1), "enemigoFacil", 0, 0);
	}

	private Enemigo crearEnemigoMedio()
	{
		return new Enemigo (10, Enemigo.setArma(1), "enemigoFacil", 0, 0);
	}
	
	private Enemigo crearEnemigoDificil()
	{
		return new Enemigo (10, Enemigo.setArma(1), "enemigoFacil", 0, 0);
	}
}
