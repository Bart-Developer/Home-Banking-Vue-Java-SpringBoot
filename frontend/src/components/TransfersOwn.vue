<template>
    <div class="childThree container">

<div class="container">
  <div class="left">
    <div class="header">
      <h2 class="animation a1 mb-5">Transfers to Own Accounts</h2>
      <h4 class="animation a2 text-white mb-5">Please complete all the fields of the form</h4>
    </div>
    <form>
    <div class="user-box">
      <select v-model="fromAccount"  class="form-select bg-success" aria-label="Default select example">
        <option selected disabled value="">Debit Account   ▼</option>
        <option v-for="x in accounts" :key="x.id" :value="x.number">SB N° {{x.number}} - ${{x.balance}}</option>
       </select>
    </div>
    <div class="user-box">
     <select v-model="toAccount" class="form-select bg-success" aria-label="Default select example">
        <option selected disabled value="">Credit Account   ▼</option>
        <option v-for="x in compareAccounts()" :key="x.id" :value="x.number">SB N° {{x.number}} - ${{x.balance}}</option>
       </select>
    </div>
    <div class="user-box">
      <input v-model="concept" type="text" name="" required="" maxlength="10" oninput="if(this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" aria-label="input the mail" aria-describedby="button-addon1"/>
      <label>Concept</label>
    </div>
    <div class="user-box">
      <input class="num" v-model="amountSelected" maxlength="12" @click="commaNums()" type="text" name="" required="">
      <label>Amount</label>
    </div>
    <a @click="transferOn()" href="#">
      <span></span>
      <span></span>
      Continue
    </a>
  </form>
  </div>
  <div class="right mt-5"></div>
</div>

<!-- modal -->

<div class="transfers modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">You are about to transfer</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
         </div>
          <div class="modal-body">
           Are you sure?
          </div>
          <div class="modal-footer">
         <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No, take me back pls</button>
        <button @click="beforeModal" type="button" data-bs-dismiss="modal" class="btn btn-success">Yes, i want do the transfer</button>
      </div>
    </div>
  </div>
</div>

<!-- toast -->

<div class="position-fixed top-0 end-0 p-3" style="z-index: 11">
  <div id="liveToast" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
      <img src="https://image.flaticon.com/icons/png/128/190/190411.png" class="rounded me-2" alt="">
      <strong class="me-auto">Successful transfer</strong>
      <small>1 second ago</small>
      <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
      <div class="toast-body">
      <p class="text-dark"> Now you can see it in the transactions section</p>
    </div>
  </div>
</div>

    </div>
</template>

<script>
    export default {

data() {
    return {
      accounts:[],
      fromAccount:"",
      toAccount:"",
      concept:"",
      amountSelected:"$00.00",
      acepted:"false",
    };
  },
  created() {

    this.accounts = this.$store.state.data.accounts;
  },
  methods:{
    compareAccounts(){
      return this.accounts.filter(x => x.number !== this.fromAccount)
    },
    commaNums(){
    this.amountSelected='';
     $("input").removeClass('bg-danger' + ' animate__animated animate__flash');
     $('input[class=num]').val('');

    function updateTextView(_obj){
        var num = getNumber(_obj.val());
        if(num==0){
        _obj.val('');
        }else{
        _obj.val(num.toLocaleString('ko-KR'));
        }
}
    function getNumber(_str){
        var arr = _str.split('');
        var out = new Array();
        for(var cnt=0;cnt<arr.length;cnt++){
        if(isNaN(arr[cnt])==false){
        out.push(arr[cnt]);
        }
    }
    return Number(out.join(''));
}
    $(document).ready(function(){
    $('input[class=num]').on('keyup',function(){
    updateTextView($(this));
    });
    });
    },
    transferOn(){

      if(this.fromAccount== ""){
       return swal({
            title: "The source account is not defined, pls try again",
            icon: "warning",
            buttons: true,
            })
      }
     if(this.toAccount == ""){
       return swal({
            title: "Destination account is not defined, pls try again",
            icon: "warning",
            buttons: true,
            })
     }
     if(this.concept == ""){
       return swal({
            title: "The concept is not defined, pls try again",
            icon: "warning",
            buttons: true,
            })
     }
    let montoSelect = parseFloat(this.amountSelected.replace(/,/g, ''));
    let [monto] = this.accounts.filter(x => x.number == this.fromAccount)
    console.log('monto seleccionado '+montoSelect)
    console.log('balance '+ monto.balance )
    if(this.amountSelected == "" || this.amountSelected == "$00.00" ||  montoSelect > monto.balance){
        
        this.amountSelected= 'Incorrect value';
        $('input[class=num]').addClass('bg-danger' + ' animate__animated animate__flash');
        return ''
        
      }

      $(".transfers").modal('show');
    },
    beforeModal(){
      this.amountSelected = parseFloat(this.amountSelected.replace(/,/g, ''))
      axios.post("/api/clients/current/transfer","amount="+this.amountSelected+"&description="+this.concept+"&originAccount="+ this.fromAccount + "&destinationAccount="+ this.toAccount)
      .then(res => {
        $(".toast").toast('show');
      setTimeout(function(){
      window.location.reload(1)
      }, 5000);

        })
      .catch(res =>{
            swal({
            title: res.response.data,
            icon: "warning",
            buttons: true,
            })
        })
        }
    }
    }

</script>

<style scoped>

.childThree{
width: 60vw;
box-sizing: border-box; }

@media only screen and (max-width: 1200px) {
    .childThree{
     width: 99vw;
    }
  } 

@import url('https://fonts.googleapis.com/css?family=Rubik:400,500&display=swap');

.img{
    width: 50vw;
}

body {
  font-family: 'Rubik', sans-serif;
}

.container {
  display: flex;
  background-image:linear-gradient(to right, #0a504a, #30c569) !important;
}

.left {
  overflow: hidden;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  justify-content: center;
  animation-name: left;
  animation-duration: 1s;
  animation-fill-mode: both;
  animation-delay: 1s;
}

.right {
  flex: 1;
  background-color: black;
  transition: 1s;
  background-image: url("https://www.bbva.com/wp-content/uploads/2019/11/transferencias2-1920x1180.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  height: 80vh;
  border-radius: 5%;
}

.header > h2 {
  margin: 0;
  color: #edecf0;
}

.header > h4 {
  margin-top: 10px;
  font-weight: normal;
  font-size: 15px;
  color: rgba(0,0,0,.4);
}

.form {
  max-width: 80%;
  display: flex;
  flex-direction: column;
}

.form > p {
  text-align: right;
}

.form > p > a {
  color: #000;
  font-size: 14px;
}

.form-field {
  height: 46px;
  padding: 0 16px;
  border: 2px solid #ddd;
  border-radius: 4px;
  font-family: 'Rubik', sans-serif;
  outline: 0;
  transition: .2s;
  margin-top: 20px;
}

.form-field:focus {
  border-color: #0f7ef1;
}

.animation {
  animation-name: move;
  animation-duration: .4s;
  animation-fill-mode: both;
  animation-delay: 2s;
}

.a1 {
  animation-delay: 2s;
}

.a2 {
  animation-delay: 2.1s;
}

.a3 {
  animation-delay: 2.2s;
}

.a4 {
  animation-delay: 2.3s;
}

.a5 {
  animation-delay: 2.4s;
}

.a6 {
  animation-delay: 2.5s;
}

@keyframes move {
  0% {
    opacity: 0;
    visibility: hidden;
    transform: translateY(-40px);
  }

  100% {
    opacity: 1;
    visibility: visible;
    transform: translateY(0);
  }
}

@keyframes left {
  0% {
    opacity: 0;
    width: 0;
  }

  100% {
    opacity: 1;
    padding: 20px 40px;
    width: 440px;
  }
}

/*nuevo*/

.form-select{
    background-image: linear-gradient(to right, #0a504a, #30c569) !important;
}

.user-box {
  position: relative;
}

.user-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}

.user-box select{
width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}
.user-box label {
  position: absolute;
  top:0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: .5s;
}

.user-box input:focus ~ label,
.user-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #03e9f4;
  font-size: 12px;
}

form a {
  position: relative;
  display: inline-block;
  padding: 10px 20px;
  color: #03e9f4;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: .5s;
  margin-top: 40px;
  letter-spacing: 4px
}

a:hover {
  background: #03f490;
  color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 5px #03f490,
              0 0 15px #03f490,
              0 0 25px #03f490,
              0 0 35px #03e9f4;
}

a span {
  position: absolute;
  display: block;
}

a span:nth-child(1) {
  top: 0;
  left: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg, transparent, #03f46f);
  animation: btn-anim1 1s linear infinite;
}

@keyframes btn-anim1 {
  0% {
    left: -100%;
  }
  50%,100% {
    left: 100%;
  }
}

a span:nth-child(2) {
  top: -100%;
  right: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(180deg, transparent, #03e9f4);
  animation: btn-anim2 1s linear infinite;
  animation-delay: .25s
}

@keyframes btn-anim2 {
  0% {
    top: -100%;
  }
  50%,100% {
    top: 100%;
  }
}

a span:nth-child(3) {
  bottom: 0;
  right: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(270deg, transparent, #03e9f4);
  animation: btn-anim3 1s linear infinite;
  animation-delay: .5s
}

@keyframes btn-anim3 {
  0% {
    right: -100%;
  }
  50%,100% {
    right: 100%;
  }
}

a span:nth-child(4) {
  bottom: -100%;
  left: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #03e9f4);
  animation: btn-anim4 1s linear infinite;
  animation-delay: .75s
}

@keyframes btn-anim4 {
  0% {
    bottom: -100%;
  }
  50%,100% {
    bottom: 100%;
  }
}

</style>