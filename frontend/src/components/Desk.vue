<template>
    <div id="childOne">

  <div class="acordion d-flex flex-column">

<div class="accordion" id="accordionPanelsStayOpenExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="panelsStayOpen-headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
        Accounts
      </button>
    </h2>
    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">

  <div class="accordion-body" v-for="x in accounts" :key="x.id">
   <div class="one d-flex flex-row justify-content-between">
    <div class="one-one min-vw-50">
       <span class="fw-bold me-3">Savings bank</span> <br>
        $  {{x.number}}
    </div>
      <div>
            Available balance <br>
         <span class="fw-bold">$ {{x.balance.toString()
                          .replace(/\B(?=(\d{3})+(?!\d))/g, ",")}}</span>
      </div>
      <div>
        <div class="dropdown">
          <button class="btn btn-outline-success dropdown-toggle fw-bold" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
            To Transfer
          </button>
          <ul class="dropdown-menu dropdown-menu-light" aria-labelledby="dropdownMenuButton2">
          <li><a @click="ref('transfersOwn')" class="dropdown-item text-secondary" href="#">Own accounts</a></li>
          <li><hr class="dropdown-divider"></li>
          <li><a @click="ref('transfersOther')" class="dropdown-item text-secondary" href="#">Other Accounts</a></li>
         </ul>
        </div>
    </div>
</div>
<hr style="width:100%;" />
</div><!-- fin accordion body -->

    </div>
  </div>
</div>

<div class="accordion" id="accordionPanelsStayOpenExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="true" aria-controls="panelsStayOpen-collapseTwo">
        Cards
      </button>
    </h2>
    <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingTwo">
      <div class="accordion-body">

     <div class="d-flex flex-row justify-content-between">
        <div class="div 1">
            <h6 class="mb-3">Gold N° 2588 5355 7768 5458</h6>
            <p>Expiration</p>
            <p>Balance in $</p>
            <p>Minimum payment</p>
        </div>
        <div class="div 2 me-3 fw-bold">
            <p> </p>
            <p>12/07/2021</p>
            <p>$ 3.222,49</p>
            <p>$ 3.222,00</p>
        </div>
      </div>

      </div>
    </div>
  </div>
</div>

<div class="accordion" id="accordionPanelsStayOpenExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="panelsStayOpen-headingThree">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="true" aria-controls="panelsStayOpen-collapseThree">
        Current quotes
      </button>
    </h2>
    <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingThree">
      <div class="accordion-body">

        <!-- contenido 3er acordion -->
        <div class="one d-flex flex-row justify-content-between text-secondary pb-3">
            <div class="one-one">
              <span class="fw-bold">Currency</span> <br>
                <img src="https://www.dolarsi.com/func/img/dolarusa.png" alt="altText"/> Dollars
            </div>
            <div class="one-two">
                <span class="fw-bold"> Buy</span><br>
            $ 95,25
            </div>
            <div class="one-three">
              <span class="fw-bold">Sale</span> <br>
            101,25
            </div>
        </div>

      </div>
    </div>
  </div>
</div>

</div>
    </div>
</template>

<script>

    export default {
    data(){
      return {
        accounts:[],
      }
    },
    created(){

      axios.get('/api/clients/current')
      .then(({data}) => {
        this.accounts = data.accounts;
        this.$store.commit('setData', data);
      })
      .catch(console.warn);
    },
    methods:{
      ref(x){
        this.$emit('setView', x)
      }
    },
    beforeMount () {
      axios.get('/api/clients/current')
      .then(({data}) => {
        this.accounts = data.accounts;
        this.$store.commit('setData', data);
      });
    },

    }
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css2?family=Anton&display=swap');

.accordion-body{
  font-style: italic;
  text-shadow: 3px 5px 15px #ffffff;
}

.perrito:nth-last-child(1){
  margin-bottom: 0!important;
}

.accordion-button::after {
    background-image: url('../assets/boton.png');
}

.accordion-button:not(.collapsed)::after{
   background-image: url('../assets/boton.png');
}

.accordion-item{
 min-width: 50vw;

}

.accordion-button {
   color: white;
   font-weight: bolder;
    background: linear-gradient(to right, #0a504a, #30c569) !important;

}

.accordion-button:not(.collapsed) {
    color: white;
    font-weight: bolder;
    background: linear-gradient(to right, #0a504a, #30c569) !important;
}

.vue{
     color: linear-gradient(to right, #0a504a, #30c569) !important;
}

.accordion-body{
  background-image: url('https://img.freepik.com/foto-gratis/texturas-pared-blanca_74190-6114.jpg?size=626&ext=jpg');
  background-repeat: no-repeat;
  background-size: cover;
  padding-bottom: 0;
}


@media screen and (max-width: 800px) {
    .accordion-item{
        width: 93vw;
        margin-top: 13px;
        margin-bottom: 5px;
    }
    
}
</style>