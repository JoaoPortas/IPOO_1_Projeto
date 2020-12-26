/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 


 *
 * @author João Gouveia e João Portas

 public class MenuHandler{

    private MenuType currentMenu = null;
    private MenuType[] possibleChoises = null;


    public MenuHandler(MenuType initialMenu){
        if (currentMenu == null){
            this.currentMenu = initialMenu;
            this.possibleChoises = new Menutype[5];
        }
    }

    private MenuType[] getAvailableOptions(MenuType menuToCheck){

    }

    public void drawMenu(){

    }

    public void handleInput(){
        changeMenu(this.possibleChoises[input]);
    } 

    private void changeMenu(MenuType newMenu){
        this.currentMenu = newMenu;
        this.possibleChoises = new Menutype[5];
        drawMenu();
    }
}
 */