package com.example.tfmtest.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ProvinciasCantonesDistritos {


    public ProvinciasCantonesDistritos() {
    }
    public void provinciasHM() {
        HashMap<String, String> provincias = new HashMap<String, String>();
        provincias.put("2", "Alajuela");
        provincias.put("1", "San José");
        provincias.put("3", "Cartago");
        provincias.put("4", "Heredia");
        provincias.put("5", "Guanacaste");
        provincias.put("6", "Puntarenas");
        provincias.put("7", "Limón");
    }
    public HashMap<Integer, String[]> scantones() {
        HashMap<Integer, String[]> provincias = new HashMap<Integer, String[]>();
        provincias.put(1,cantones1sj());
        provincias.put(2,cantonesAl2());
        provincias.put(3,cantonesC1());
        provincias.put(4,cantonesH1());
        provincias.put(5,cantonesG1());
        provincias.put(6,cantonesP1());
        provincias.put(7,cantonesL1());
        return provincias;
    }
    public HashMap<Integer, HashMap<Integer, String[]> > scantonesDist() {
        HashMap<Integer, HashMap<Integer, String[]> >  provincias = new HashMap<Integer, HashMap<Integer, String[]>>();
        provincias.put(1,distritosSJ());
        provincias.put(2,distritosAl());
        provincias.put(3,distritosCa());
        provincias.put(4,distritosHe());
        provincias.put(5,distritosGu());
        provincias.put(6,distritosPu());
        provincias.put(7,distritosLi());
        return provincias;
    }
    public HashMap<Integer, String[]>  distritosSJ() {
        HashMap<Integer, String[]>  distrito = new HashMap<Integer, String[]> ();
        distrito.put(1,distritos1sj());
        distrito.put(2,distritos2sj());
        distrito.put(3,distritos3sj());
        distrito.put(4,distritos4sj());
        distrito.put(5,distritos5sj());
        distrito.put(6,distritos6sj());
        distrito.put(7,distritos7sj());
        distrito.put(8,distritos8sj());
        distrito.put(9,distritos9sj());
        distrito.put(10,distritos10sj());
        distrito.put(11,distritos11sj());
        distrito.put(12,distritos12sj());
        distrito.put(13,distritos13sj());
        distrito.put(14,distritos14sj());
        distrito.put(15,distritos15sj());
        distrito.put(16,distritos16sj());
        distrito.put(17,distritos17sj());
        distrito.put(18,distritos18sj());
        distrito.put(19,distritos19sj());
        distrito.put(20,distritos20sj());
        return distrito;

    }
    public HashMap<Integer, String[]>  distritosAl() {
        HashMap<Integer, String[]> distrito = new HashMap<Integer, String[]>();
        distrito.put(1, distritosAl1());
        distrito.put(2, distritosAl2());
        distrito.put(3, distritosAl3());
        distrito.put(4, distritosAl4());
        distrito.put(5, distritosAl5());
        distrito.put(6, distritosAl6());
        distrito.put(7, distritosAl7());
        distrito.put(8, distritosAl8());
        distrito.put(9, distritosAl9());
        distrito.put(10, distritosAl10());
        distrito.put(11, distritosAl11());
        distrito.put(12, distritosAl12());
        distrito.put(13, distritosAl13());
        distrito.put(14, distritosAl14());
        distrito.put(15, distritosAl15());
        distrito.put(16, distritosAl16());
        return distrito;
    }
    public HashMap<Integer, String[]>  distritosCa() {
        HashMap<Integer, String[]> distrito = new HashMap<Integer, String[]>();
        distrito.put(1,distritosC1());
        distrito.put(2,distritosC2());
        distrito.put(3,distritosC3());
        distrito.put(4,distritosC4());
        distrito.put(5,distritosC5());
        distrito.put(6,distritosC6());
        distrito.put(7,distritosC7());
        distrito.put(8,distritosC8());
        return distrito;
    }
    public HashMap<Integer, String[]>  distritosHe() {
        HashMap<Integer, String[]> distrito = new HashMap<Integer, String[]>();
        distrito.put(1,distritosH1());
        distrito.put(2,distritosH2());
        distrito.put(3,distritosH3());
        distrito.put(4,distritosH4());
        distrito.put(5,distritosH5());
        distrito.put(6,distritosH6());
        distrito.put(7,distritosH7());
        distrito.put(8,distritosH8());
        distrito.put(9,distritosH9());
        distrito.put(10,distritosH10());
        return distrito;
    }
    public HashMap<Integer, String[]>  distritosGu() {
        HashMap<Integer, String[]> distrito = new HashMap<Integer, String[]>();
        distrito.put(1,distritosG1());
        distrito.put(2,distritosG2());
        distrito.put(3,distritosG3());
        distrito.put(4,distritosG4());
        distrito.put(5,distritosG5());
        distrito.put(6,distritosG6());
        distrito.put(7,distritosG7());
        distrito.put(8,distritosG8());
        distrito.put(9,distritosG9());
        distrito.put(10,distritosG10());
        distrito.put(11,distritosG11());
        return distrito;
    }
    public HashMap<Integer, String[]>  distritosPu() {
        HashMap<Integer, String[]> distrito = new HashMap<Integer, String[]>();
        distrito.put(1,distritosP1());
        distrito.put(2,distritosP2());
        distrito.put(3,distritosP3());
        distrito.put(4,distritosP4());
        distrito.put(5,distritosP5());
        distrito.put(6,distritosP6());
        distrito.put(7,distritosP7());
        distrito.put(8,distritosP8());
        distrito.put(9,distritosP9());
        distrito.put(10,distritosP10());
        distrito.put(11,distritosP11());
        return distrito;
    }
    public HashMap<Integer, String[]>  distritosLi() {
        HashMap<Integer, String[]> distrito = new HashMap<Integer, String[]>();
        distrito.put(1,distritosL1());
        distrito.put(2,distritosL2());
        distrito.put(3,distritosL3());
        distrito.put(4,distritosL4());
        distrito.put(5,distritosL5());
        distrito.put(6,distritosL6());
        return distrito;
    }

    public String[] provincias() {
        String lprovincias[] = {"San José", "Alajuela", "Cartago", "Heredia", "Guanacaste", "Puntarenas", "Limón"};
        return lprovincias;
    }
    public String[] cantones1sj(){//san jose
        String lcantones1[] = {
                "Central",
                "Escazú",
                "Desamparados",
                "Puriscal",
                "Tarrazú",
                "Aserrí",
                "Mora",
                "Goicoechea",
                "Santa Ana",
                "Alajuelita",
                "Vázquez De Coronado",
                "Acosta",
                "Tibás",
                "Moravia",
                "Montes De Oca",
                "Turrubares",
                "Dota",
                "Curridabat",
                "Pérez Zeledón",
                "León Cortés Castro"};
        return lcantones1;
    }
    public String[] distritos20sj(){//san jose
        String ldistritos20[] = {
                //  nombre": "León Cortés Castro",
                "San Pablo",
                "San Andres",
                "Llano Bonito",
                "San Isidro",
                "Santa Cruz",
                "San Antonio"
        };
        return ldistritos20;
    }
    public String[] distritos19sj(){//san jose
        String ldistritos20[] = {
                //    "nombre": "Pérez Zeledón",
                "San Isidro De El General",
                "El General",
                "Daniel Flores",
                "Rivas",
                "San Pedro",
                "Platanares",
                "Pejibaye",
                "Cajon",
                "Baru",
                "Rio Nuevo",
                "Páramo"
        };
        return ldistritos20;
    }
    public String[] distritos18sj(){//san jose
        String ldistritos20[] = {
                //  nombre": Curridabat",
                "Curridabat","Granadilla","Sanchez","Tirrases"
        };
        return ldistritos20;
    }
    public String[] distritos17sj(){//san jose
        String ldistritos20[] = {
                //  nombre"Dota",
                "Santa María","Jardin","Copey"
        };
        return ldistritos20;
    }
    public String[] distritos16sj(){//san jose
        String ldistritos20[] = {
                //  nombre"Turrubare
                "San Pablo","San Pedro","San Juan De Mata",	"San Luis",	"Carara"
        };
        return ldistritos20;}
    public String[] distritos15sj(){//san jose
        String ldistritos20[] = {
                //  nombre":Montes De Oca
                "San Pedro","Sabanilla","Mercedes",	"San Rafael"
        };
        return ldistritos20;
    }
    public String[] distritos14sj(){//san jose
        String ldistritos20[] = {
                //  nombre"
                "Moravia",	"San Vicente",	"San Jeronimo",	"La Trinidad"
        };
        return ldistritos20;
    }
    public String[] distritos13sj(){//san jose
        String ldistritos20[] = {
                //  nombre":Tibás
                "San Juan",	"Cinco Esquinas",	"Anselmo Llorente",	"Leon XII",	"Colima"
        };
        return ldistritos20;
    }
    public String[] distritos12sj(){//san jose
        String ldistritos20[] = {
                //  nombre": Acosta
                "San Ignacio",	"Guaitil",	"Palmichal",	"Cangrejal","Sabanillas"
        };
        return ldistritos20;
    }
    public String[] distritos11sj(){//san jose
        String ldistritos20[] = {
                //  nombre": Vázquez De Coronado
                "San Isidro",	"San Rafael",	"Dulce Nombre De Jesus",
                "Patalillo",	"Cascajal"
        };
        return ldistritos20;
    }
    public String[] distritos10sj(){//san jose
        String ldistritos20[] = {
                //  nombre": "Alajuelita
                "Alajuelita",	"San Josecito",	"San Antonio",	"Concepción",	"San Felipe"
        };
        return ldistritos20;
    }
    public String[] distritos9sj(){//san jose
        String ldistritos20[] = {
                //  nombre": "Santa Ana
                "Santa Ana",	"Salitral",	"Pozos",	"Uruca",	"Piedades",	"Brasil"
        };
        return ldistritos20;}
    public String[] distritos8sj(){//san jose
        String ldistritos20[] = {
                //  nombre":Goicoechea",
                "Guadalupe","San Francisco","Calle Blancos",
                "Mata De Platano","Ipís","Rancho Redondo","Purral"
        };
        return ldistritos20;
    }
    public String[] distritos7sj(){//san jose
        String ldistritos20[] = {
                //  nombre": "Mora"
                "Colón","Guayabo","Tabarcia","Piedras Negras","Picagres","Jaris"
        };
        return ldistritos20;
    }
    public String[] distritos6sj(){//san jose
        String ldistritos20[] = {
                //  nombre": "Aserrí"
                "Aserrí","Tarbaca","Vuelta De Jorco","San Gabriel",
                "Legua","Monterrey","Salitrillos"
        };
        return ldistritos20;
    }
    public String[] distritos5sj(){//san jose
        String ldistritos20[] = {
                //  nombre": "Tarrazú"
                "San Marcos","San Lorenzo","San Carlos"
        };
        return ldistritos20;
    }
    public String[] distritos4sj(){//san jose
        String ldistritos20[] = {
                //  nombre": Puriscal",
                "Santiago","Mercedes Sur","Barbacoas","Grifo Alto",
                "San Rafael","Candelarita","Desamparaditos","San Antonio","Chires"
        };
        return ldistritos20;
    }
    public String[] distritos3sj(){//san jose
        String ldistritos20[] = {
                //  nombre": "Desamparados",
                "Desamparados","San Miguel","San Juan De Dios","San Rafael Arriba","San Rafael Abajo","San Antonio","Frailes","Patarra","San Cristobal","Rosario","Damas","Gravilias","Los Guido"
        };
        return ldistritos20;
    }
    public String[] distritos2sj(){//san jose
        String ldistritos20[] = {
                //  nombre": Escazú",
                "Escazú","San Antonio","San Rafael"
        };
        return ldistritos20;}
    public String[] distritos1sj(){//san jose
        String ldistritos20[] = {
                //  nombre": "Central",
                "Carmen","Merced","Hospital","Catedral",
                "Zapote","San Francisco De Dos Rios","Uruca",
                "Mata Redonda","Pavas","Hatillo","San Sebastián"
        };
        return ldistritos20;
    }


    public String[] cantonesAl2(){//alajuela
        String lcantones[] = {
            "Central","San Ramón","Grecia","San Mateo",
            "Atenas","Naranjo","Palmares","Poás","Orotina",
            "San Carlos","Zarcero","Sarchí","Upala","Los Chiles","Guatuso","Río Cuarto"
        };
        return lcantones;
    }
    public String[] distritosAl1(){//alajuela
        String ldistritos[] = {// 01","Central",
                "Alajuela","San José","Carrizal","San Antonio",
                "Guácima","San Isidro","Sabanilla","San Rafael",
                "Rio Segundo","Desamparados","Turrucares","Tambor","Garita",
                "Sarapiquí"
        };
        return ldistritos;
    }
    public String[] distritosAl2(){//alajuela
        String ldistritos[] = {
               //02","San Ramón","
                "San Ramón","Santiago","San Juan","Piedades Norte",
                "Piedades Sur","San Rafael","San Isidro","Angeles",
                "Alfaro","Volio","Concepción","Zapotal","Peñas Blancas"
        };
        return ldistritos;
    }
    public String[] distritosAl3(){//alajuela
        String ldistritos[] = { //03","Grecia"," +
             "Grecia","San Isidro","San José","San Roque",
                "Tacares","Rio Cuarto","Puente De Piedra",
                "Bolivar"
        };
        return ldistritos;
    }
    public String[] distritosAl4(){//alajuela
        String ldistritos[] = {//04","San Mateo",
        "San Mateo","Desmonte","Jesús María","Labrador"
        };
        return ldistritos;
    }
    public String[] distritosAl5(){//alajuela
        String ldistritos[] = {//05","Atenas",
             "Atenas","Jesús","Mercedes","San Isidro",
                "Concepción","San José","Santa Eulalia","Escobal"
        };
        return ldistritos;
    }
    public String[] distritosAl6(){//alajuela
        String ldistritos[] = {//06","Naranjo",
            "Naranjo","San Miguel","San José","Cirrí Sur",
            "San Jerónimo","San Juan","El Rosario","Palmitos",
        };
        return ldistritos;
    }
    public String[] distritosAl7(){//alajuela
        String ldistritos[] = {//07","Palmares",
            "Palmares","Zaragoza","Buenos Aires","Santiago",
            "Candelaria","Esquipulas","La Granja"
        };
        return ldistritos;
    }
    public String[] distritosAl8(){//alajuela
        String ldistritos[] = {//08","Poás",
                 "San Pedro","San Juan","San Rafael"
                ,"Carrillos","Sabana Redonda"
        };
        return ldistritos;
    }
    public String[] distritosAl9(){//alajuela
        String ldistritos[] = {//09","Orotina",
            "Orotina","El Mastate","Hacienda Vieja","Coyolar","La Ceiba"
        };
        return ldistritos;
    }
    public String[] distritosAl10() {//alajuela
        String ldistritos[] = {//10","San Carlos",
              "Quesada","Florencia","Buenavista","Aguas Zarcas","Venecia","Pital","La Fortuna","La Tigra","La Palmera","Venado","Cutris","Monterrey","Pocosol"
        };
        return ldistritos;
    }
    public String[] distritosAl11(){//alajuela
        String ldistritos[] = {//11","Zarcero",
            "Zarcero","Laguna","Tapesco","Guadalupe",
            "Palmira","Zapote","Brisas"
        };
        return ldistritos;
    }
    public String[] distritosAl12(){//alajuela
        String ldistritos[] = {//12","Sarchí",
        "Sarchí Norte","Sarchí Sur","Toro Amarillo","San Pedro","Rodriguez"
        };
        return ldistritos;
    }
    public String[] distritosAl13(){//alajuela
        String ldistritos[] = {//13","Upala",
            "Upala","Aguas Claras","San José o Pizote","Bijagua","Delicias","Dos Rios","Yolillal","Canalete"

        };
        return ldistritos;
    }
    public String[] distritosAl14(){//alajuela
        String ldistritos[] = {//14","Los Chiles",
            "Los Chiles","Caño Negro","El Amparo","San Jorge"
        };
        return ldistritos;
    }
    public String[] distritosAl15(){//alajuela
        String ldistritos[] = {//15","Guatuso",
            "San Rafael","Buenavista","Cote","Katira"
        };
        return ldistritos;
    }
    public String[] distritosAl16(){//alajuela
        String ldistritos[] = {//16","Río Cuarto",
            "Río Cuarto"
        };
        return ldistritos;
    }
    public String[] cantonesC1(){//cartago
        String lcantones[] = {
            "Central","Paraíso","La Unión","Jiménez","Turrialba","Alvarado","Oreamuno","El Guarco"
        };
        return lcantones;
    }
    public String[] distritosC1(){//cartago
        String ldistritos[] = {//01","Central","
            "Oriental","Occidental","Carmen","San Nicolás","Aguacaliente o San Francisco","Guadalupe o Arenilla","Corralillo","Tierra Blanca","Dulce Nombre","Llano Grande","Quebradilla"
        };
        return ldistritos;
    }
    public String[] distritosC2(){//cartago
        String ldistritos[] = { //02","Paraíso",
            "Paraiso","Santiago","Orosi","Cachí","Llanos de Santa Lucía"
        };
        return ldistritos;
    }
    public String[] distritosC3(){//cartago
        String ldistritos[] = {//03","La Unión",
            "Tres Rios","San Diego","San Juan","San Rafael","Concepción","Dulce Nombre","San Ramón","Rio Azul"
        };
        return ldistritos;
    }
    public String[] distritosC4(){//cartago
        String ldistritos[] = {//04","Jiménez",
            "Juan Viñas","Tucurrique","Pejibaye"
        };
        return ldistritos;
    }
    public String[] distritosC5(){//cartago
        String ldistritos[] = {//05","Turrialba",
            "Turrialba","La Suiza","Peralta","Santa Cruz","Santa Teresita","Pavones","Tuis","Tayutic","Santa Rosa","Tres Equis","La Isabel","Chirripó"
        };
        return ldistritos;
    }
    public String[] distritosC6(){//cartago
        String ldistritos[] = {//06","Alvarado",
            "Pacayas","Cervantes","Capellades"
        };
        return ldistritos;
    }
    public String[] distritosC7(){//cartago
        String ldistritos[] = {//07","Oreamuno",
            "San Rafael","Cot","Potrero Cerrado","Cipreses","Santa Rosa"
        };
        return ldistritos;
    }
    public String[] distritosC8() {//cartago
        String ldistritos[] = {//08","El Guarco",
                "El Tejar", "San Isidro", "Tobosi", "Patio De Agua"
        };
        return ldistritos;
    }
    public String[] cantonesH1(){//Heredia
        String lcantones[] = {
            "Central","Barva","Santo Domingo","Santa Barbara","San Rafael","San Isidro","Belén","Flores","San Pablo","Sarapiquí"
        };
        return lcantones;
    }
    public String[] distritosH1(){//Heredia
        String ldistritos[] = {//01","Central",
            "Heredia","Mercedes","San Francisco","Ulloa","Varablanca"
        };
        return ldistritos;
    }
    public String[] distritosH2(){//Heredia
        String ldistritos[] = {//02","Barva",
            "Barva","San Pedro","San Pablo","San Roque","Santa Lucía","San José de la Montaña"
        };
        return ldistritos;
    }
    public String[] distritosH3(){//Heredia
        String ldistritos[] = {//03","Santo Domingo",
            "Santo Domingo","San Vicente","San Miguel","Paracito","Santo Tomás","Santa Rosa","Tures","Para"        };
        return ldistritos;
    }
    public String[] distritosH4(){//Heredia
        String ldistritos[] = {//04","Santa Barbara",
            "Santa Bárbara","San Pedro","San Juan","Jesús","Santo Domingo","Puraba"
        };
        return ldistritos;
    }
    public String[] distritosH5(){//Heredia
        String ldistritos[] = {//05","San Rafael",
            "San Rafael","San Josecito","Santiago","Los Ángeles","Concepción"
        };
        return ldistritos;
    }
    public String[] distritosH6(){//Heredia
        String ldistritos[] = {//06","San Isidro",
            "San Isidro","San José","Concepción","San Francisco"
        };
        return ldistritos;
    }
    public String[] distritosH7(){//Heredia
        String ldistritos[] = {//07","Belén",
            "San Antonio","La Ribera","La Asuncion"
        };
        return ldistritos;
    }
    public String[] distritosH8(){//Heredia
        String ldistritos[] = {//08","Flores",
            "San Joaquín","Barrantes","Llorente"
        };
        return ldistritos;
    }
    public String[] distritosH9(){//Heredia
        String ldistritos[] = {//09","San Pablo",
            "San Pablo","Rincon De Sabanilla"
        };
        return ldistritos;
    }
    public String[] distritosH10(){//Heredia
        String ldistritos[] = {//10","Sarapiquí
            "Puerto Viejo","La Virgen","Las Horquetas","Llanuras Del Gaspar","Cureña"
        };
        return ldistritos;
    }
    public String[] cantonesG1(){//guanacaste
        String lcantones[] = {
            "Liberia","Nicoya","Santa Cruz","Bagaces","Carrillo","Cañas","Abangares","Tilarán","Nandayure","La Cruz","Hojancha"
        };
        return lcantones;
    }
    public String[] distritosG1(){//guanacaste
        String ldistritos[] = {// 01","Liberia",
            "Liberia","Cañas Dulces","Mayorga","Nacascolo","Curubande"
        };
        return ldistritos;
    }
    public String[] distritosG2(){//guanacaste
        String ldistritos[] = {//02","Nicoya",
            "Nicoya","Mansión","San Antonio","Quebrada Honda","Sámara","Nosara","Belén De Nosarita"
        };
        return ldistritos;
    }
    public String[] distritosG3(){//guanacaste
        String ldistritos[] = {//03","Santa Cruz",
            "Santa Cruz","Bolson","Veintisiete de Abril","Tempate","Cartagena","Cuajiniquil","Diria","Cabo Velas","Tamarindo"
        };
        return ldistritos;
    }
    public String[] distritosG4(){//guanacaste
        String ldistritos[] = {//04","Bagaces",
            "Bagaces","La Fortuna","Mogote","Rio Naranjo"
        };
        return ldistritos;
    }
    public String[] distritosG5(){//guanacaste
        String ldistritos[] = {//05","Carrillo",
            "Filadelfia","Palmira","Sardinal","Belen"
        };
        return ldistritos;
    }
    public String[] distritosG6(){//guanacaste
        String ldistritos[] = {//06","Cañas",
            "Cañas","Palmira","San Miguel","Bebedero","Porozal"
        };
        return ldistritos;
    }
    public String[] distritosG7(){//guanacaste
        String ldistritos[] = {//07","Abangares",
            "Las Juntas","Sierra","San Juan","Colorado"
        };
        return ldistritos;
    }
    public String[] distritosG8(){//guanacaste
        String ldistritos[] = {//08","Tilarán",
            "Tilarán","Quebrada Grande","Tronadora","Santa Rosa","Líbano","Tierras Morenas","Arenal"
        };
        return ldistritos;
    }
    public String[] distritosG9(){//guanacaste
        String ldistritos[] = {//09","Nandayure",
            "Carmona","Santa Rita","Zapotal","San Pablo","Porvenir","Bejuco"
        };
        return ldistritos;
    }
    public String[] distritosG10(){//guanacaste
        String ldistritos[] = {//10","La Cruz",
            "La Cruz","Santa Cecilia","La Garita","Santa Elena"
        };
        return ldistritos;
    }

    public String[] distritosG11(){//guanacaste
        String ldistritos[] = {//11","Hojancha",
            "Hojancha","Monte Romo","Puerto Carrillo","Huacas"
        };
        return ldistritos;
    }

    public String[] cantonesP1(){//Puntarenas
        String lcantones[] = {
            "Central","Esparza","Buenos Aires","Montes De Oro","Osa","Quepos","Golfito","Coto Brus","Parrita","Corredores","Garabito"
        };
        return lcantones;
    }
    public String[] distritosP1(){//Puntarenas
        String ldistritos[] = {//00","Central",
            "Puntarenas","Pitahaya","Chomes","Lepanto","Paquera","Manzanillo","Guacimal","Barranca","Monte Verde","Isla Del Coco","Cóbano","Chacarita","Chira","Acapulco","El Roble","Arancibia"
        };
        return ldistritos;
    }
    public String[] distritosP2(){//Puntarenas
        String ldistritos[] = {//01","Esparza",
            "Espíritu Santo","San Juan Grande","Macacona","San Rafael","San Jerónimo"

        };
        return ldistritos;
    }

    public String[] distritosP3(){//Puntarenas
        String ldistritos[] = {//03","Buenos Aires",
            "Buenos Aires","Volcán","Potrero Grande","Boruca","Pilas","Colinas","Changuena","Biolley","Brunka"
        };
        return ldistritos;
    }
    public String[] distritosP4(){//Puntarenas
        String ldistritos[] = {//04","Montes De Oro",
                "Miramar","La Unión","San Isidro"
        };
        return ldistritos;
    }
    public String[] distritosP5(){//Puntarenas
        String ldistritos[] = {//05","Osa",
            "Puerto Cortés","Palmar","Sierpe","Bahía Ballena","Piedras Blancas","Bahía Drake"
        };
        return ldistritos;
    }
    public String[] distritosP6(){//Puntarenas
        String ldistritos[] = {//06","Quepos",
            "Quepos","Savegre","Naranjito"
        };
        return ldistritos;
    }
    public String[] distritosP7(){//Puntarenas
        String ldistritos[] = {//07","Golfito",
            "Golfito","Puerto Jiménez","Guaycara","Pavón"
        };
        return ldistritos;
    }
    public String[] distritosP8(){//Puntarenas
        String ldistritos[] = {//08","Coto Brus",
            "San Vito","Sabalito","Aguabuena","Limoncito","Pittier"
        };
        return ldistritos;
    }
    public String[] distritosP9(){//Puntarenas
        String ldistritos[] = {//09","Parrita"
            "Parrita"
        };
        return ldistritos;
    }
    public String[] distritosP10(){//Puntarenas
        String ldistritos[] = {//10","Corredores"
            "Corredor","La Cuesta","Canoas","Laurel"
        };
        return ldistritos;
    }
    public String[] distritosP11(){//Puntarenas
        String ldistritos[] = {//11","Garabito"
            "Jacó","Tárcoles"
        };
        return ldistritos;
    }


    public String[] cantonesL1(){//Limón
        String lcantones[] = {//
                "Central","Pococí",
            "Siquirres","Talamanca","Matina","Guácimo"
        };
        return lcantones;
    }
    public String[] distritosL1(){//Limón
        String ldistritos[] = {//01","Central",
            "Limón","Valle La Estrella","Rio Blanco","Matama"
        };
        return ldistritos;
    }
    public String[] distritosL2(){//Limón
        String ldistritos[] = {//02","Pococí",
            "Guapiles","Jiménez","Rita","Roxana","Cariari","Colorado","La Colonia"
        };
        return ldistritos;
    }
    public String[] distritosL3(){//Limón
        String ldistritos[] = {//03","Siquirres",\
            "Siquirres","Pacuarito","Florida","Germania","El Cairo","Alegría"
        };
        return ldistritos;
    }
    public String[] distritosL4(){//Limón
        String ldistritos[] = {//04","Talamanca",
            "Bratsi","Sixaola","Cahuita","Telire"
        };
        return ldistritos;
    }
    public String[] distritosL5(){//Limón
        String ldistritos[] = {//05","Matina",
            "Matina","Batán","Carrandi"
        };
        return ldistritos;
    }
    public String[] distritosL6(){//Limón
        String ldistritos[] = {//06","Guácimo",
            "Guácimo","Mercedes","Pocora","Rio Jiménez","Duacari"
        };
        return ldistritos;
    }

}

