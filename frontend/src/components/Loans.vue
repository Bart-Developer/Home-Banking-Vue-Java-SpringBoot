<template>
        <div class="loans">


    <div class="acordion d-flex flex-column">

    <div class="accordion mb-5" id="accordionPanelsStayOpenExample">
    <div class="accordion-item">
        <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
        <button class="accordion-button" type="button" data-bs-toggle="collapse"  aria-expanded="true" aria-controls="panelsStayOpen-collapseTwo">
            Loans
        </button>
        </h2>
        <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingTwo">
        <div class="accordion-body">

     <div v-for="x in loans" :key="x.id">
        
        <div class="notice notice-lg text-center">
           <strong> Type Loan:  {{x.name}}</strong>
        <div class="content d-flex flex-row justify-content-between text-left gap-3">
            <div class="content-1">
                Acquisition date: <br>
                Amount Request: <br>
                Agreed fees: <br>
            </div>
            <div class="content-2">
                {{x.dateRequest}} <br>
                 ${{x.amount.toLocaleString('ko-KR')}} <br>
                ({{x.payment}}) fees <br>
            </div>
        </div>
        
        </div>
         
         <hr class="d-flex flex-column" style="width:100%;" />
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
            return{
                loans:[],
        }},
        created(){

            axios.get('/api/clients/current').then(({data}) => this.loans = data.loans.sort((a,b) => parseInt(b.clientLoanId) - (a.clientLoanId)))
            
            // Implements vuex
            // this.loans = this.$store.state.data.loans.sort((a,b) => parseInt(b.clientLoanId) - (a.clientLoanId));
        }
    }
</script>

<style scoped>

.notice {
    padding: 15px;
    background-color: #fafafa;
    margin-bottom: 10px;
    -webkit-box-shadow: 0 5px 8px -6px rgba(0,0,0,.2);
       -moz-box-shadow: 0 5px 8px -6px rgba(0,0,0,.2);
        box-shadow: 0 5px 8px -6px rgba(0,0,0,.2);

  padding: 1rem;
  border-width: 6px;
  border-left-style: solid;
  border-image: 
    linear-gradient(
      to bottom, 
      #0a504a, #30c569
    ) 1 100%;
}

.accordion-button:not(.collapsed)::after{
    background-image:unset!important;
}
.accordion-button::after {
    background-image: unset !important;
}

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
 margin-top: 10px;

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
        width: 97vw
    }
    
}

</style>