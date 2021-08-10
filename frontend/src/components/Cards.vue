<template>
    <div class="cardss d-flex flex-column justify-content-center align-items-center">

    <Modal/>

        <div class="caja  mt-4">
            <div class="barra d-grid gap-2 d-flex justify-content-between">
                <h4 class="text-white fw-bold ms-2 pt-2 me-2">Cards</h4>
                <div>
                <a class="btn btn-success btn-lg btn-sm me-2 mt-1 mb-1 " href="#" role="button" data-bs-toggle="modal" data-bs-target="#exampleModal"><i class="fas fa-credit-card mt-2 me-1"></i>New Card</a>
                <div @mouseleave="deleteNum = false" class="btn-group me-2">
                <button @click="paintNum()" v-if="cards.length > 0" class="btn btn-danger btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                   <i class="fas fa-trash pe-1"></i> Delete Card
                </button>
                <ul class="dropdown-menu bg-danger">
                <li @mouseover="deleteNum = true" v-for="x in cards" :key="x.id"><a @click="modalCard(x.cardId)" class="dropdown-item text-white" data-bs-toggle="modal" data-bs-target="#staticBackdrop" href="#">Choose: N°  {{x.cardId}}</a></li>
                </ul>
                </div>
                </div>
            </div>
        </div>
    <div class="caja-content d-flex flex-column">

            <div v-if="cards.length == 0" class="content-tittle  mb-3">
                <h4 class="subtittles text-center pt-2 ps-2 pb-2 mt-4">You don't have any card</h4>
            </div>

            <div v-if="cards.filter(x => x.typecard == 'CREDIT').length > 0" class="content-tittle  mb-3">
                <h4 class="subtittles pt-2 ps-2 pb-2">Credit Cards</h4>
            </div>
        <div class="d-flex flex-row gap-3 flex-wrap justify-content-center">
            <div v-for="x in dependsCard('CREDIT')" :key="x.id" class="card">
            <div class="card__front card__part" :class="x.colorCard">
                <div class="eliminar">
                <h1 v-if="deleteNum == true">{{x.cardId}}</h1>
                </div>
                <p v-if="x.colorCard == 'GOLD'" class="gold card__front-square card__square">GOLD</p>
                <p v-else-if="x.colorCard == 'PLATINUM'" class="platinum card__frnpm ont-square card__square">PLATINUM</p>
                <p v-else class="titanium card__front-square card__square">TITANIUM</p>
                <p v-if="x.typecard == 'DEBIT'" class="debit card__front-logo card__logo">DEBIT</p>
                <p v-else class="credit card__front-logo card__logo">CREDIT</p>
                <p class="card_numer">{{x.number}}</p>
                <div class="card__space-75">
                <span class="card__label">Card holder</span>
                <p class="card__info">{{x.cardholder}}</p>
                </div>
                <div class="card__space-25 text-center">
                <span class="card__label">From</span>
                    <p class="card__info">{{x.fromDate}}</p>
                </div>
                 <div class="card__space-25 text-center">
                <span class="card__label">Expires</span>
                    <p class="card__info">{{x.thruDate}}</p>
                </div>
            </div>

            <div class="card__back card__part" :class="x.colorCard">
                <div class="card__black-line"></div>
                <div class="card__back-content">
                <div class="card__secret">
                    <p class="card__secret--last">{{x.cvv}}</p>
                </div>
                <img class="card__back-square card__square" src="https://static-s.aa-cdn.net/img/gp/20600000099048/CV44ii3zy147i-nxYbr7l85mbXETs1VFf3DTW2qTXzn6ON9jgyvU0LpiVQGsfZbF8Q=w300?v=1">
                <img class="card__back-logo card__logo" src="https://image.flaticon.com/icons/png/128/368/368200.png">
                </div>
            </div>
            </div>
        </div>

            <div v-if="cards.filter(x => x.typecard == 'DEBIT').length > 0" class="content-tittle mb-3">
                <h4 class="subtittles mt-3 pt-2 ps-2 pb-2">Debits Cards</h4>
            </div>

            <div class="d-flex flex-row gap-3 flex-wrap justify-content-center mb-2">
            <div v-for="x in dependsCard('DEBIT')" :key="x.id" class="card mb-2">
            <div class="card__front card__part" :class="x.colorCard">
                <div class="eliminar">
                <h1 v-if="deleteNum == true">{{x.cardId}}</h1>
                </div>
                <p v-if="x.colorCard == 'GOLD'" class="gold card__front-square card__square">GOLD</p>
                <p v-else-if="x.colorCard == 'PLATINUM'" class="platinum card__front-square card__square">PLATINUM</p>
                <p v-else class="titanium card__front-square card__square">TITANIUM</p>
                <p v-if="x.typecard == 'DEBIT'" class="debit card__front-logo card__logo">DEBIT</p>
                <p v-else class="credit card__front-logo card__logo">CREDIT</p>
                <p class="card_numer">{{x.number}}</p>
                <div class="card__space-75">
                <span class="card__label">Card holder</span>
                <p class="card__info">{{x.cardholder}}</p>
                </div>
                <div class="card__space-25 text-center">
                <span class="card__label">From</span>
                    <p class="card__info">{{x.fromDate}}</p>
                </div>
                 <div class="card__space-25 text-center">
                <span class="card__label">Expires</span>
                    <p class="card__info">{{x.thruDate}}</p>
                </div>
            </div>

            <div class="card__back card__part" :class="x.colorCard">
                <div class="card__black-line"></div>
                <div class="card__back-content">
                <div class="card__secret">
                    <p class="card__secret--last">{{x.cvv}}</p>
                </div>
                <img class="card__back-square card__square" src="https://static-s.aa-cdn.net/img/gp/20600000099048/CV44ii3zy147i-nxYbr7l85mbXETs1VFf3DTW2qTXzn6ON9jgyvU0LpiVQGsfZbF8Q=w300?v=1">
                <img class="card__back-logo card__logo" src="https://image.flaticon.com/icons/png/128/368/368200.png">
                </div>
            </div>
            </div>
        </div>

    <!-- fin caja -->
    </div>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">Are you sure do you want eliminate this card?</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <!-- tarjeta modal -->

       <div class="d-flex flex-row gap-3 flex-wrap justify-content-center mb-2">
            <div v-for="x in cardModal" :key="x.id" class="card mb-2">
            <div class="card__front card__part" :class="x.colorCard">
                <div class="eliminar">
                <h1 v-if="deleteNum == true">{{x.cardId}}</h1>
                </div>
                <p v-if="x.colorCard == 'GOLD'" class="gold card__front-square card__square">GOLD</p>
                <p v-else-if="x.colorCard == 'PLATINUM'" class="platinum card__front-square card__square">PLATINUM</p>
                <p v-else class="titanium card__front-square card__square">TITANIUM</p>
                <p v-if="x.typecard == 'DEBIT'" class="debit card__front-logo card__logo">DEBIT</p>
                <p v-else class="credit card__front-logo card__logo">CREDIT</p>
                <p class="card_numer">{{x.number}}</p>
                <div class="card__space-75">
                <span class="card__label">Card holder</span>
                <p class="card__info">{{x.cardholder}}</p>
                </div>
                <div class="card__space-25 text-center">
                <span class="card__label">From</span>
                    <p class="card__info">{{x.fromDate}}</p>
                </div>
                 <div class="card__space-25 text-center">
                <span class="card__label">Expires</span>
                    <p class="card__info">{{x.thruDate}}</p>
                </div>
            </div>

            <div class="card__back card__part" :class="x.colorCard">
                <div class="card__black-line"></div>
                <div class="card__back-content">
                <div class="card__secret">
                    <p class="card__secret--last">{{x.cvv}}</p>
                </div>
                <img class="card__back-square card__square" src="https://static-s.aa-cdn.net/img/gp/20600000099048/CV44ii3zy147i-nxYbr7l85mbXETs1VFf3DTW2qTXzn6ON9jgyvU0LpiVQGsfZbF8Q=w300?v=1">
                <img class="card__back-logo card__logo" src="https://image.flaticon.com/icons/png/128/368/368200.png">
                </div>
            </div>
            </div>
        </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No, Take me Back</button>
        <button @click="eliminateCard()" type="button" class="btn btn-danger">Yes, i do</button>
      </div>
    </div>
  </div>
</div>
<!-- fin modal -->

<div class="w3-panel w3-green w3-display-container w-50 top-0 position-absolute animate__animated">
  <h2 class="mt-2 mb-3 text-center">Success!</h2>
  <hr>
  <h3 class="mt-2 text-center">the card was deleted</h3>
</div>

    </div>
</template>

<script>

import Modal from '../components/Modal.vue'
import 'w3-css/w3.css';
    export default {
        components:{Modal},
        props:['Message'],
        
        data(){
        return {
        cards:[],
        deleteNum:false,
        cardModal:[],
        }
        },
        created(){

            axios.get('api/clients/current').then(({data}) =>{ this.cards = data.cards })
            // this.cards = this.$store.state.data.cards.sort((b,a) => parseInt(b.cardId) - (a.cardId));
        },
        methods:{

            dependsCard: function(type) {return this.cards.filter(x => x.typecard == type)},

            modalCard: function(id) {
                 if(window.innerWidth <= 768){
                        this.deleteNum = false}
                return this.cardModal = this.cards.filter(x => x.cardId == id)},
                

            eliminateCard(){
                $('.modal').hide();
                $('.modal-backdrop').hide();
                $('.w3-panel').show()
                $('.w3-panel').addClass("animate__slideInRight")
                setTimeout(function(){
                    $('.w3-panel').removeClass("animate__slideInRight")
                    }, 2500);
                setTimeout(function(){
                    $('.w3-panel').addClass("animate__zoomOutLeft")
                    }, 3500);
                setTimeout(function(){
                     $('.w3-panel').removeClass("animate__animated animate__zoomOutLeft")
                    }, 3600);    
                setTimeout(function(){
                     $('.w3-panel').hide();
                    }, 3600);    
                   

                    const [cardSelected] = this.cardModal; // Desestructuracion de arreglos
                    axios.delete('/api/cards/'+ cardSelected.cardId).then(()=>{
                    setTimeout(() => this.$emit("setView", "cards"), 4000)
                    }
                    );
                },
                
                paintNum(){
                    if(window.innerWidth <= 768){
                     if (this.deleteNum == true){
                         this.deleteNum=false;
                     }else this.deleteNum=true;
                    }
                       
                }
            }
        }
        
</script>

<style lang="scss" scoped>

.w3-panel{
    display: none;
    height: 14vh;
    background: linear-gradient(to right, #0a504a, #30c569)!important;
}

.dropdown-menu *:hover{
    color: black;
    background: #a52d2d!important;
}

.cardss{
    width: 80vw;
    min-height: 79vh;
}

h1{
    color: black;
}

.eliminar{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 20%;
    position: fixed;
    top: 35%;
    left: 40%;
    background-color: #ebebeb;
    border-radius: 10%;
    *{
        color: red;
    }
}

.subtittles{
    color: white;
    background: linear-gradient(to right, #062e2b, #1a743c) !important;
    box-sizing:border-box;
}

.caja{
    background: linear-gradient(to right, #0a504a, #30c569) !important;
    width: 60vw;
}
.caja-content{
   background-image: url(https://img.freepik.com/foto-gratis/texturas-pared-blanca_74190-6114.jpg?size=626&ext=jpg);
   background-size: cover;
   width: 60vw;
}

.modal-content{
background-image: url(https://img.freepik.com/foto-gratis/texturas-pared-blanca_74190-6114.jpg?size=626&ext=jpg);
background-size: cover;
}

@media only screen and (max-width: 850px) {
    .caja{
      width: 96vw;
    }
    .caja-content{
        width: 96vw;
        margin-bottom: 10px;
    }
  }

.container{
    margin:0;
    padding:0;
}
/* ESTILOS TARJETAS*/

@import url('https://fonts.googleapis.com/css?family=Space+Mono:400,400i,700,700i');

.gold{

color: yellow;
background: #f8e807;
-webkit-background-clip: text;
-moz-background-clip: text;
background-clip: text;
color: transparent;
text-shadow: 0px 3px 3px rgba(255, 255, 255, 0.192),0px -1px 1px rgba(0,0,0,.3);
font-size: 15px;
font-weight: bolder;
}

.platinum{
color: #e5e4e2;
background: #e5e4e2;
-webkit-background-clip: text;
-moz-background-clip: text;
background-clip: text;
color: transparent;
text-shadow: 0px 3px 3px rgba(255, 255, 255, 0.192),0px -1px 1px rgba(0,0,0,.3);
font-size: 15px;
font-weight: bolder;
}

.titanium{

background: #878681;
-webkit-background-clip: text;
-moz-background-clip: text;
background-clip: text;
color: transparent;
text-shadow: 0px 3px 3px rgba(255, 255, 255, 0.192),0px -1px 1px rgba(0,0,0,.3);
font-size: 15px;
font-weight: bolder;
}

.debit{
text-align: left; 
font-family: Arial Black; 
font-weight: bold; font-size: 15px; 
background: #202020; 
-webkit-background-clip: text; 
-moz-background-clip: text; 
background-clip: text; 
color: transparent; 
text-shadow: 0px 3px 3px rgba(255,255,255,0.4),0px -1px 1px rgba(0,0,0,0.3);
}

.credit{
text-align: left; 
font-family: Arial Black; 
font-weight: bold; font-size: 15px; 
background: #202020; 
-webkit-background-clip: text; 
-moz-background-clip: text; 
background-clip: text; 
color: transparent; 
text-shadow: 0px 3px 3px rgba(255,255,255,0.4),0px -1px 1px rgba(0,0,0,0.3);
}

.card *{
  box-sizing:border-box;
  font-family: 'Space Mono', monospace;
}
.card{
    width: 320px;
    height: 190px;
    -webkit-perspective: 600px;
    -moz-perspective: 600px;
    perspective:600px;
    background-image: url(https://img.freepik.com/foto-gratis/texturas-pared-blanca_74190-6114.jpg?size=626&ext=jpg);
}

.card__part{
    box-shadow: 1px 1px #aaa3a3;
    top: 0;
    position: absolute;
    z-index: 1000;
    left: 0;
    display: inline-block;
    width: 320px;
    height: 190px;
    background-image: url('https://image.ibb.co/bVnMrc/g3095.png'), linear-gradient(to right bottom, #fd8369, #fc7870, #f96e78, #f56581, #ee5d8a);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    border-radius: 8px;

    -webkit-transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
    -moz-transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
    -ms-transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
    -o-transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
    transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
    -webkit-transform-style: preserve-3d;
    transform-style: preserve-3d;
    -moz-transform-style: preserve-3d;
    -webkit-backface-visibility: hidden;
    backface-visibility: hidden;
    -moz-backface-visibility: hidden;
}

.card__front{
  padding: 18px;
  transform: rotateY(0);
-webkit-transform: rotateY(0);
-moz-transform: rotateY(0);
}

.card__back {
  padding: 18px 0;
  transform: rotateY(-180deg);
-webkit-transform: rotateY(-180deg);
-moz-transform: rotateY(-180deg);
}

.card__black-line {
    margin-top: 5px;
    height: 38px;
    background-color: #303030;
}

.card__logo {
    height: 30px;
}

.card__front-logo{
      position: absolute;
    top: 18px;
    right: 18px;
}
.card__square {
    border-radius: 5px;
    height: 30px;
}

.card_numer {
    display: block;
    width: 100%;
    word-spacing: 4px;
    font-size: 20px;
    letter-spacing: 2px;
    color: #fff;
    text-align: center;
    margin-bottom: 20px;
    margin-top: 20px;
}

.card__space-75 {
    width: 50%;
    float: left;
}

.card__space-25 {
    width: 25%;
    float: left;
}

.card__label {
    font-size: 10px;
    text-transform: uppercase;
    color: rgba(255,255,255,0.8);
    letter-spacing: 1px;
}

.card__info {
    margin-bottom: 0;
    margin-top: 5px;
    font-size: 16px;
    line-height: 18px;
    color: #fff;
  letter-spacing: 1px;
  text-transform: uppercase;
}

.card__back-content {
    padding: 15px 15px 0;
}
.card__secret--last {
    color: #303030;
    text-align: right;
    margin: 0;
    font-size: 14px;
}

.card__secret {
    padding: 5px 12px;
    background-color: #fff;
    position:relative;
}

.card__secret:before{
    content:'';
    position: absolute;
    top: -3px;
    left: -3px;
    height: calc(100% + 6px);
    width: calc(100% - 42px);
    border-radius: 4px;
    background: repeating-linear-gradient(45deg, #ededed, #ededed 5px, #f9f9f9 5px, #f9f9f9 10px);
}

.card__back-logo {
    position: absolute;
    bottom: 15px;
    right: 15px;
}

.card__back-square {
    position: absolute;
    bottom: 15px;
    left: 15px;
}

.card:hover .card__front {
    -webkit-transform: rotateY(180deg);
    transform: rotateY(180deg);
    -moz-transform: rotateY(180deg);

}

.card:hover .card__back {
    -webkit-transform: rotateY(0deg);
    transform: rotateY(0deg);
    -moz-transform: rotateY(0deg);
}


/* COLORES */ 

.GOLD {
    background:conic-gradient(#edc800, #e3b600, #f3cf00, #ffe800,
                                #edc800, #e3b600, #f3cf00, #ffe800,
                                #edc800, #e3b600, #f3cf00, #ffe800,
                                #edc800, #e3b600, #f3cf00, #ffe800);
                text-shadow: 2px 2px 2px #558ABB;
}

.PLATINUM {
    background:conic-gradient(#d7d7d7, #c3c3c3, #cccccc, #c6c6c6,
                                #d7d7d7, #c3c3c3, #cccccc, #c6c6c6,
                                #d7d7d7, #c3c3c3, #cccccc, #c6c6c6,
                                #d7d7d7, #c3c3c3, #cccccc, #c6c6c6);
                text-shadow: 2px 2px 2px #558ABB;
}

.TITANIUM {
    background:conic-gradient(#e6e9bf, #d2b5aa, #cbaea3, #d4b5ab,
                                #e6e9bf, #d2b5aa, #cbaea3, #d4b5ab,
                                #e6e9bf, #d2b5aa, #cbaea3, #d4b5ab,
                                #e6e9bf, #d2b5aa, #cbaea3, #d4b5ab);
                text-shadow: 2px 2px 2px #558ABB;
}

</style>