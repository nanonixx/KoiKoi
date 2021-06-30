package com.myproject;


import com.badlogic.gdx.Game;
import com.myproject.Config.BaseScreen;
import com.myproject.Screens.MainMenuScreen;

public class MyGame extends Game {

	Assets assets;

	@Override
	public void create () {



		assets = new Assets();
		assets.load();
		setScreen(new MainMenuScreen(this));
	}

	public BaseScreen getBaseScreen(){
		return (BaseScreen) getScreen();
	}
}


