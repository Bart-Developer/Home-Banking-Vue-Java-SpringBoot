<template>
    <div class="wrapper w-100 h-100 bg-secondary">

        <!-- nav -->
    <div class="collapse" id="navbarToggleExternalContent">
      <div class="bg-light p-4">
        <h5 class="text-white h4">Collapsed content</h5>
        <span class="text-muted">Toggleable via the navbar brand.</span>
        </div>
    </div>
    <nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
    <button @click="openCanvas()" class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
    </button>
    <h5 class="text-secondary  me-1 fs-6 position-relative">{{client.firstName}} {{client.lastName}} <img class="imgPerfil" src="../assets/imgperfil.jpg" alt="altText"/>  | <a href="#"><i class="fas fa-bell fa-2x position-relative mt-1">
    <span class="num position-absolute top-0 start-100 translate-middle badge rounded-pill bg-success">
    99+
    <span class="visually-hidden">unread messages</span>
    </span>
    </i></a></h5>
    </div>
  </nav>

  <!-- navegacion url -->
  <div class="contenidoToTheRight d-flex justify-content-end">
  <nav aria-label="breadcrumb" class="barUrl d-flex flex-row justify-content-start pt-3 ps-3">
    <ol class="breadcrumb pe-1 ps-1">
      <li class="breadcrumb-item"><a href="#" @click="toggle = 'desk'" class="fw-bold">Desk</a></li>
      <li v-if="toggle == 'accounts'" class="breadcrumb-item active fw-bold" aria-current="page">Accounts</li>
      <li v-if="toggle =='transfersOther' " class="breadcrumb-item active fw-bold" aria-current="page">Transfers /   Other Accounts</li>
      <li v-if="toggle =='transfersOwn'" class="breadcrumb-item active fw-bold" aria-current="page">Transfers /   Own Accounts</li>
      <li v-if="toggle == 'cards'" class="breadcrumb-item active fw-bold" aria-current="page">Cards</li>
      <li v-if="toggle == 'loans'" class="breadcrumb-item active fw-bold" aria-current="page">Loans</li>
    </ol>
  </nav>
  </div>



<div v-if="toggle == 'loans'" class="contenidoToTheRight d-flex justify-content-end">
  <div class="getNewLoans d-flex flex-row bg-info ps-5 pt-3 text-white">
    <p><i class="fas fa-exclamation-circle"></i> Monday to Friday from 00:00 to 18:00 hs. , Saturdays and Sundays 24 hours a day, reserve your loan of up to $ 400,000.00  <button @click="toggle='modalLoans'" type="button" class="btn btn-outline-light pb-2 fw-bold">Request Now</button></p>
  </div>
</div>

<!-- off canvas -->
<div class="offcanvas offcanvas-start show" data-bs-scroll="true" data-bs-backdrop="false" data-bs-keyboard="false"  tabindex="-1" id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
  <div @click="closeCanvasMobile()" class="close"></div>
  <div class="offcanvas-header bg-verdeLinear d-flex flex-column">
    <img class="w-25" src="../assets/loguito.png" alt="logo"/>
  </div>
  <div class="offcanvas-body bg-verdeLinear m-0 p-0">
      <!-- accordion -->
    <div class="accordion accordion-flush" id="accordionFlushExample">

   <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingOne">
     <button @click="toggleView('desk')" class="accordion-button wArrows bg-verdeLinear" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
        Desk
      </button>
      <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample"></div>
    </h2>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <div id="flush-collapseOne" class="accordion-collapse collapse show" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample"></div>
    </h2>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingTwo">
      <button class="accordion-button bg-verdeLinear collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
        Accounts
      </button>
    </h2>
    <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">

        <button @click="toggleView('accounts')" class="accordion-button wArrows collapsed bg-verdeLinear" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseAccounts" aria-expanded="false" aria-controls="flush-collapseAccounts">
          Check Accounts
        </button>

    </div>

  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingThree">
      <button class="accordion-button collapsed bg-verdeLinear" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
        Transfers
      </button>
    </h2>
    <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">

        <button @click="toggleView('transfersOwn')" class="accordion-button wArrows collapsed bg-verdeLinear" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseAccounts" aria-expanded="false" aria-controls="flush-collapseAccounts">
          Own Accounts
        </button>

        <button @click="toggleView('transfersOther')" class="accordion-button wArrows collapsed bg-verdeLinear" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseAccounts" aria-expanded="false" aria-controls="flush-collapseAccounts">
          Other Accounts
        </button>

    </div>
  </div>

   <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingFour">
     <button @click="toggleView('cards')" class="accordion-button wArrows collapsed bg-verdeLinear" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFour" aria-expanded="false" aria-controls="flush-collapseFour">
        Cards
      </button>
      <div id="flush-collapseFour" class="accordion-collapse collapse" aria-labelledby="flush-headingFour" data-bs-parent="#accordionFlushExample"></div>
    </h2>
  </div>

 <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingFive">
     <button @click="toggleView('loans')" class="accordion-button wArrows collapsed bg-verdeLinear" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseFive" aria-expanded="false" aria-controls="flush-collapseFive">
        Loans
      </button>
      <div id="flush-collapseFive" class="accordion-collapse collapse" aria-labelledby="flush-headingFive" data-bs-parent="#accordionFlushExample"></div>
    </h2>
  </div>

   <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingSix">
     <button @click="logoff()" class="accordion-button wArrows collapsed bg-verdeLinear" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseSix" aria-expanded="false" aria-controls="flush-collapseSix">
        Sign Out
      </button>
      <div id="flush-collapseSix" class="accordion-collapse collapse" aria-labelledby="flush-headingSix" data-bs-parent="#accordionFlushExample"></div>
    </h2>
  </div>

<!-- fin offcanvas -->
    </div>
  </div>
</div>

<!-- main -->
<div class="contenidoToTheRight d-flex justify-content-end"> 

<main class="main bg-secondary">
  
 <div v-if="toggle=='modalLoans'">
 <ModalLoans @setView="forceRerender"/>
 </div>

 <div>
   <Modal @setView="forceRerender"/>
 </div>

 <div v-if="toggle == 'desk'">
  <Desk @setView="forceRerender"/>
   </div>

  <div v-if="toggle == 'accounts'">
    <Accounts @setView="forceRerender"/>
  </div>

  <div v-if="toggle == 'transfersOwn'">
    <TransfersOwn/>
  </div>

  <div v-if="toggle == 'transfersOther'">
    <TransfersOther/>
  </div>

  <div v-if="toggle == 'cards'">
    <Cards @setView="forceRerender"/>
  </div>
  <div v-if="toggle == 'loans'">
  <Loans/>
  </div>
</main>

</div>

<!-- footer -->

<footer class="bottom-0 bg-light d-flex flex-row justify-content-end">
  <h1><button type="button" class="btn btn-primary btn-floating mx-1">
      <i class="fab fa-facebook-f"></i>
    </button>

    <button type="button" class="btn btn-primary btn-floating mx-1">
      <i class="fab fa-google"></i>
    </button>

    <button type="button" class="btn btn-primary btn-floating mx-1">
      <i class="fab fa-twitter"></i>
    </button>

    <button type="button" class="btn btn-primary btn-floating mx-1">
      <i class="fab fa-github"></i>
    </button></h1>
</footer>

    </div>
</template>

<script>

import Desk from '../components/Desk.vue'
import Accounts from '../components/Accounts.vue'
import TransfersOwn from '../components/TransfersOwn.vue'
import TransfersOther from '../components/TransfersOther.vue'
import Cards from '../components/Cards.vue'
import Loans from '../components/Loans.vue'
import Modal from '../components/Modal.vue'
import ModalLoans from '../components/ModalLoans.vue'

export default {
  components: { Desk, Accounts, TransfersOwn, TransfersOther, Cards, Loans, Modal, ModalLoans },

      data(){
      return {
        toggle: "desk",
        client:[],
      }
    },
    created(){
    },
    methods:{
      toggleView(x){
        if(x == 'accounts' && this.toggle == 'accounts' ){
        this.toggle = 'desk';
        this.$nextTick (() => {
          this.toggle = 'accounts';
        });
        }else this.toggle=x;

        if(window.innerWidth <= 768){
          $('#offcanvasScrolling').css('display','none');
        }
      },
      forceRerender(payload) {
        
        this.toggle = 'desk';

        this.$nextTick (() => {
          this.toggle = payload;
        });
      },
      logoff(){
      axios.post('/api/logout').then(response => console.log('signed out!!!'))
      sessionStorage.clear();
      location.reload();
      },
      openCanvas(){
         $('#offcanvasScrolling').css('display','flex')
      },
      closeCanvasMobile(){
        $('#offcanvasScrolling').css('display','none')
        $('#offcanvasScrolling').removeAttr( 'style' );
      }
    },
    mounted(){

      $('#offcanvasScrolling').css('visibility','visible')
      // document.getElementById('offcanvasScrolling').style.visibility="visible"
    },
    
    }
</script>

<style lang="scss" scoped>

#offcanvasScrolling{
  visibility: visible;
}

.a{
  color:#0d99fd,
}

.num{
  font-size: 10px;
}

#wrapper{
    height: 100vh;
    background-color:  white;
}

.offcanvas{
  max-width: 40%!important;
}

.bg-verdeLinear{
background: linear-gradient(to right, #0a504a, #30c569) !important;
}

.accordion-button{
    border: 1px solid white;
    color: white;
    font-weight: bold;
    border-right: none;
    border-top: none;
    border-left: none;
    box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -webkit-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -moz-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
}

.accordion-item button{
    position: relative;
    display: flex;
    align-items: center;
    width: 100%;
    padding: 1rem 1.25rem;
    font-size: 1rem;
    border: 1px solid white;
    color: white;
    text-align: left;
    font-weight: bold;
    border-right: none;
    border-top: none;
    border-left: none;
    box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -webkit-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -moz-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
}

.accordion-button:hover{
  text-shadow: 1px 1px #000201;
  &:hover {
    animation: swipe 0.3s 1;
    animation-delay: 0s;
    animation-direction: linear;
  }
}

@keyframes swipe {
  @for $i from 0 through 100 {
    $s: $i + "%";
    #{$s} {
      -webkit-mask: -webkit-gradient(radial, 30 25, $i+23, 20 20, $i+38, from(rgb(0, 0, 0)), color-stop(0.5, rgba(0, 0, 0, 0)), to(rgb(0, 0, 0)));
    }
  }
}

.accordion-item button:active{
  background-color: #32acaf;
}

.accordion-button.button{
    color: white;
}

.wArrows::after{
  background-image: none!important;
}

.accordion-button::after {
    background-image: url('../assets/boton.png');
}

.accordion-button:not(.collapsed){
  color: white;
  box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -webkit-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -moz-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
  background: linear-gradient(to right, #062e2b, #1a743c) !important;
}

.accordion-button:not(.collapsed)::after{
  background-image: url('../assets/boton.png');
  transform: rotate(93deg);
  border-left: 0px solid #32acaf;
  transition: border-left 0.7s;

}

.accordion-button:not(.collapsed):hover {
  background-color: #473828;
  color: rgba(255, 255, 255, 1) !important;
  border-left: 10px solid #32acaf;
  transition: border-left 0.2s;
  box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -webkit-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -moz-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
}

.accordion-button:focus{
  border-color: white;
  box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -webkit-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
    -moz-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
}

.barUrl{
width: 80%;
box-shadow: rgba(50, 50, 93, 0.25) 0px 30px 60px -12px inset, rgba(0, 0, 0, 0.3) 0px 18px 36px -18px inset;
background-color: rgb(197, 203, 209);
}
.main{
  width: 79%;
  min-height: 80vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.getNewLoans{
  width: 79%;
}

footer{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.imgPerfil{
 width: 60px;
 border-radius: 80%;
}

.offcanvas-body{

box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
-webkit-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
-moz-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
}
.offcanvas-header{
box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
-webkit-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
-moz-box-shadow: -21px -1px 0px -4px rgba(188,230,208,1) inset;
}

@media only screen and (max-width: 1200px) {
   
   #offcanvasScrolling {
     max-width: 80%!important;
     display: none;
    }
    .getNewLoans, .main , .barUrl {
      width: 100vw;
    }
    #footer{
      margin-left: 0;
    }
  }

.close {
  display:none;
  background: #474747;
  height: 30px;
  width: 30px;
  border: 3px solid #FFF;
  border-radius: 50%;
  position: absolute;
  top: 0;
  right: 15px;
  cursor: pointer;
  transition: .4s .3s;
   &:before, &:after {
    content: '';
    background: #FFF;
    height: 80%;
    width: 3px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%) rotate(-45deg);
  }
  &:after {
   transform: translate(-50%, -50%) rotate(45deg); 
  }
}

@media only screen and (max-width: 768px) {
    .close{
      display: inherit;
    }
  }
</style>