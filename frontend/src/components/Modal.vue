<template>
    <div class="modalVue">

<div
      class="modal fade"
      id="exampleModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content p-4">
          <button @click="close()"
            type="button"
            class="close x"
            data-dismiss="modal"
            aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
          <div class="modal-body">
            <h3 class="mb-4 title d-flex justify-content-start">New Card</h3>
            <div class="mt-3 text-start">
              <small>Select the type of card you need</small>
              <div class="d-flex flex-row mt-3 mb-2">
                <label class="radio1">
                  <input @change="typeCard('DEBIT')" type="radio" name="payment" value="debit" required />
                  <span> DEBIT CARD</span>
                </label>
                <label class="radio1">
                  <input  @change="typeCard('CREDIT')" type="radio" name="payment" value="credit" required />
                  <span>CREDIT CARD</span>
                </label>
              </div>
              <small>Select the color of card you need</small>
              <div
                class="mt-3 d-flex flex-row justify-content-center gap-3 fw-bold"
              >
                <label class="radio">
                  <input @change="colorCard('GOLD')" type="radio" name="week" /> <span>GOLD</span>
                </label>
                <label class="radio">
                  <input @change="colorCard('PLATINUM')" type="radio" name="week" />
                  <span>PLATINUM</span>
                </label>
                <label class="radio">
                  <input @change="colorCard('TITANIUM')" type="radio" name="week" />
                  <span>TITANIUM</span>
                </label>
              </div>
            </div>

            <!-- comienzo tarjeta -->
            <div class="d-flex flex-column align-items-center">
              <div class="card mt-2">
                <div class="card__front card__part" :class="colorCardSelected">
                  <p class="card__front-square card__square"></p>
                  <p class="card__front-logo card__logo">{{typeCardSelected}}</p>
                  <p class="card_numer">**** **** **** 6258</p>
                  <div class="card__space-75">
                    <span class="card__label">Card holder</span>
                    <p class="card__info">{{client.firstName + ' ' + client.lastName}}</p>
                  </div>
                  <div class="card__space-25">
                    <span class="card__label">Expires</span>
                    <p class="card__info"> {{new Date().getMonth()+'/' + yearAfter.toString().slice(2, 4) }} </p>
                  </div>
                </div>

                <div class="card__back card__part" :class="colorCardSelected">
                  <div class="card__black-line"></div>
                  <div class="card__back-content">
                    <div class="card__secret">
                      <p class="card__secret--last">420</p>
                    </div>
                    <img
                      class="card__back-square card__square"
                      src="https://images.sftcdn.net/images/t_app-logo-l,f_auto/p/0fe5f4e6-577a-4949-9893-5be29d5b5ef8/235082830/banelco-movil-icon.png"
                    />
                    <p class="card__back-logo card__logo"></p>
                  </div>
                </div>
              </div>
            </div>
            <!-- fin tarjeta -->

            <div class="mt-3 mr-2">
              <div class="row g-2">
                <div class="col-md-6">
                  <div class="inputbox">
                    <small>Card Number</small>
                    <input
                      type="text"
                      class="form-control"
                      name=""
                      disabled
                      value="5588 5554 5554 8854"
                    />
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="inputbox">
                    <small>Card Holder</small>
                    <input
                      type="text"
                      class="form-control"
                      name=""
                      :value="[[client.firstName]] + ' ' + [[client.lastName]]"
                      disabled
                    />
                  </div>
                </div>
              </div>
            </div>
            <div class="mt-3 mr-2">
              <div class="row g-2">
                <div class="col-md-6">
                  <div class="row g-2">
                    <div class="col-md-6">
                      <div class="inputbox">
                        <small>From</small>
                        <input
                          type="text"
                          class="form-control"
                          name=""
                          :value="
                            [[new Date().toString().slice(4, 10)]] +
                              ' ' +
                              [[year]]
                          "
                          disabled
                        />
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="inputbox">
                        <small>Thru</small>
                        <input
                          type="text"
                          class="form-control"
                          name=""
                          :value="
                            [[new Date().toString().slice(4, 10)]] +
                              ' ' +
                              [[yearAfter.toString().slice(2, 4)]]
                          "
                          disabled
                        />
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-md-6 ">
                  <div class="inputbox ">
                    <small>CVV</small>
                    <input
                      type="text"
                      class="form-control text-center"
                      name=""
                      value="132"
                      disabled
                    />
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer border-0 mb-4 form-button">
            <button  @click="addCard()"
              id="submit"
              type="submit"
              class="btn signup col-6 col-md-6"
              data-dismiss="modal"
            >
              APPLY
            </button>
          </div>
        </div>
      </div>
    </div>

    </div>
</template>

<script>
import swal from 'sweetalert';

export default {
  components: {  },

  data() {
    return {
      client: [],
      year: new Date()
        .getFullYear()
        .toString()
        .slice(2, 4),
      yearAfter: new Date().getFullYear() + 1,
      typeCardSelected:"",
      colorCardSelected:"",
      size:0,
    };
  },
  created() {
    this.client = this.$store.state.data;
  },
  methods: {
    typeCard(x) {
          this.typeCardSelected=x;
      },
    colorCard(x){
       this.colorCardSelected=x;
    },
    addCard(){
        if(this.typeCardSelected == ""){
           return swal({
            title: "Please choose some type of card",
            icon: "warning",
            buttons: true,
            })
        }
        if(this.colorCardSelected == ""){
            return swal({
            title: "Please choose some type of color for the card",
            icon: "warning",
            buttons: true,
            })
        }
        axios.post('/api/clients/current/cards',"typeCard="+this.typeCardSelected+"&colorCard="+this.colorCardSelected)
        .then(res =>{
           $('.modal').hide();
           $('.modal-backdrop').hide();
           this.$emit("setView", "cards")})
        .catch(res =>{
            swal({
            title: res.response.data,
            icon: "warning",
            buttons: true,
            })
            console.clear();
            })
    },
    close(){
        $('.modal').modal('hide')
        console.log("entre")
    }

  },
};

</script>

<style scoped>

/*estilos tarjeta modelo*/

@import url("https://fonts.googleapis.com/css?family=Space+Mono:400,400i,700,700i");

body {
  display: flex;
  justify-content: center;
  align-items: center;
  display: -webkit-flex;
  -webkit-align-items: center;
  -webkit-justify-content: center;
  background-image: linear-gradient(
    to right top,
    #a2beeb,
    #8ecfee,
    #8edde5,
    #a4e7d5,
    #c7eec7
  );
  flex-direction: column;
  -webkit-flex-direction: column;
}

.card {
  width: 320px;
  height: 190px;
  -webkit-perspective: 600px;
  -moz-perspective: 600px;
  perspective: 600px;
  background-image: radial-gradient(
    circle 919px at 1.7% 6.1%,
    rgb(12, 12, 12) 0%,
    rgba(14, 14, 14, 0.85) 100.2%
  );
}

.card__part {
  box-shadow: 1px 1px #aaa3a3;
  top: 0;
  position: absolute;
  z-index: 1000;
  left: 0;
  display: inline-block;
  width: 320px;
  height: 190px;
  background-image: linear-gradient(
    to right bottom,
    #16cea6,
    #16cea6,
    #16cea6,
    #50f1ae,
    #48ec9ac0);
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  border-radius: 8px;

  -webkit-transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
    -moz-transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
    -ms-transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
    -o-transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
    transition: all .4s cubic-bezier(0.25, 0.685, 0.32, 0.125);
  transform-style: preserve-3d;
  -webkit-transform-style: preserve-3d;
  -moz-transform-style: preserve-3d;
  backface-visibility: hidden;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
}

.card__front {
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
  height: 16px;
}

.card__front-logo {
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
  width: 75%;
  float: left;
}

.card__space-25 {
  width: 25%;
  float: left;
}

.card__label {
  font-size: 10px;
  text-transform: uppercase;
  color: rgba(255, 255, 255, 0.8);
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
  position: relative;
}

.card__secret:before {
  content: "";
  position: absolute;
  top: -3px;
  left: -3px;
  height: calc(100% + 6px);
  width: calc(100% - 42px);
  border-radius: 4px;
  background: repeating-linear-gradient(
    45deg,
    #ededed,
    #ededed 5px,
    #f9f9f9 5px,
    #f9f9f9 10px
  );
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
  transform: rotateY(180deg);
  -webkit-transform: rotateY(180deg);
  -moz-transform: rotateY(180deg);
}

.card:hover .card__back {
  transform: rotateY(0deg);
  -webkit-transform: rotateY(0deg);
  -moz-transform: rotateY(0deg);
}

/*COLORES TARJETAS*/

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



/*estilos form*/

body {
  background-color: #7e57c2;
}

.container {
  margin-top: 100px;
}

.form-group {
  position: relative;
  margin-bottom: 1.5rem;
}

.form-control:focus + .form-control-placeholder,
.form-control:valid + .form-control-placeholder {
  font-size: 75%;
  transform: translate3d(0, -100%, 0);
  opacity: 1;
}

.form-control {
  background: transparent;
  border: none;
  border-bottom: 1px solid grey;
}

input.form-control:focus,
input.form-control:after {
  outline-width: 0;
  border-bottom: 1px solid aqua;
  background: transparent;
}

.modal-content {
  color: white;
  background-image: radial-gradient(
    circle 919px at 1.7% 6.1%,
    rgb(12, 12, 12) 0%,
    rgba(14, 14, 14, 0.85) 100.2%
  );
}

.modal {
  background-image: radial-gradient(
    circle 919px at 1.7% 6.1%,
    rgba(41, 58, 76, 0.075) 0%,
    rgba(40, 170, 226, 0.096) 100.2%
  );
}

.tc {
  font-size: 12px;
}

.modal-footer {
  justify-content: flex-start;
}

.signup {
  background: linear-gradient(90deg, #0a504a, #30c569) !important;
  border-radius: 34rem;
  border: none;
  color: white;
  font-size: 14px;
  font-weight: bold;
  height: 46px;
}

.close {
  color: aqua;
  font-size: 12px;
  position: relative;
  top: 20px;
}

h5 {
  font-size: 2rem;
  font-weight: 900;
}

.title {
  font-size: 2.25rem;
  font-weight: 700;
}

.x {
  color: grey;
  font-size: 40px;
  font-weight: 100;
  display: flex;
  flex-direction: row-reverse;
  top: 0;
}

.btn.focus,
.btn:focus {
  outline: 0;
  box-shadow: none !important;
}

.x.focus,
.x:focus {
  outline: 0;
  box-shadow: none !important;
}


/*CHANGE OTHER THING */

.close:hover {
  color: #000;
}

.form-check-input:focus {
  border-color: #8bbafe;
  outline: 0;
  box-shadow: none;
}

label.radio1 {
  cursor: pointer;
  width: 100%;
  margin-right: 7px;
}

label.radio1 input {
  position: absolute;
  top: 0;
  left: 0;
  visibility: hidden;
  pointer-events: none;
}

label.radio1 span {
  padding: 20px 12px;
  border: 2px solid #21cc99;
  display: inline-block;
  color: #eeeeee;
  border-radius: 3px;
  text-transform: capitalize;
  width: 100%;
  font-weight: bold;
  text-align: center;
  font-size: 14px;
}

label.radio1 input:checked + span {
  border-color: rgba(0, 49, 78, 1);
  background-color: #21cc99;
  color: #fff;
}

.form-control {
  border: 2px solid #21cc99;
}

.form-control:focus {
  box-shadow: none;
  border-color: rgba(0, 49, 78, 1);
  color: white;
}

input[type="text"]:disabled {
  background-image: radial-gradient(
    circle 919px at 1.7% 6.1%,
    rgb(12, 12, 12) 0%,
    rgba(14, 14, 14, 0.85) 100.2%
  );
  color: rgba(255, 255, 255, 0.541);
}
input[type="text"]:disabled:hover {
  border-color: rgba(0, 49, 78, 1);
  background-color: #21cc99;
  color: #fff;
}

/*radios colores targetas*/

label.radio span {
  padding: 20px 12px;
  border: 2px solid #8f37aa;
  display: inline-block;
  color: #8f37aa;
  border-radius: 3px;
  text-transform: capitalize;
  width: 100%;
}

label.radio {
  cursor: pointer;
}

label.radio input {
  position: absolute;
  top: 0;
  left: 0;
  visibility: hidden;
  pointer-events: none;
}

label.radio span {
  padding: 7px 12px;
  border: 2px solid #21cc99;
  display: inline-block;
  color: #ffffff;
  border-radius: 3px;
  text-transform: capitalize;
}

label.radio input:checked + span {
  border-color: rgba(0, 49, 78, 1);
  background-color: #21cc99;
  color: #fff;
}

/*MEDIA QUERYS*/

@media only screen and (max-width: 850px) {
  #banners img{
    display: none;
    width: 0%;
    height: 0%;
  }
  #papa{
      flex-direction: column!important;
      align-content: center!important;
      justify-content: center!important;
      align-items: center!important;
  }
}

</style>