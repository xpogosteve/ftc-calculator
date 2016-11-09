package code.model;

import code.view.CalculatorUI;

public class Calculator {
	private CalculatorUI _c;
	private int _score = 0;
	private int _spinnerValue = 0;
	private int _cornerValue = 0;
	public Calculator(CalculatorUI c){
		_c = c;
	}
	
	public void clearScoreKeyPressed(){
		_c.clearBoxes();
		_score = 0;
		_c.update();
	}
	public int getScore() {
		return _score;
	}

	public void beaconLit(boolean b) {
		if(b){
			_score = _score + 30;
			_c.update();
		}
		else{
			_score = _score - 30;
			_c.update();
		}
	}

	public void picChanged(int value) {
		if(_spinnerValue < value){
			_spinnerValue = value;
			_score = _score + 15;
			_c.update();
		}
		else{
			_score = _score - 15;
			_spinnerValue = value;
			_c.update();
		}
	}

	public void cornChanged(int value) {
		if(_cornerValue < value){
			_cornerValue = value;
			_score = _score + 5;
			_c.update();
		}
		else{
			_score = _score - 5;
			_cornerValue = value;
			_c.update();
		}
	}
	
	
}
