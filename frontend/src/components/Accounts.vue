<template>
    <div class="childTwo bg-light d-flex flex-column">

<transition name="bounce">
        <div v-if="show" class="caja bg-light container">
            <div class="barra gap-2 d-flex justify-content-between">
                <h4 class="text-white fw-bold ms-2 pt-2 me-2">Accounts</h4>
                <a class="btn btn-success  btn-lg btn-sm me-2 mt-1 mb-1" href="#" role="button" @click="openModal()" data-bs-target="#exampleModal"><i class="fas fa-user-plus mt-2 me-1"></i>new account</a>
            </div>

<!-- modal -->
          <div class="modal account fade animate__animated animate__backInUp" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="text-white fs-4" id="exampleModalLabel">- You are about to create a new account, are you sure to create it?</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body d-flex justify-content-center">
                  <button @click="addAccount()" type="submit" class="btn btn-success fs-5 me-3 fw-bold">GET IT NOW <i class="fas fa-star"></i></button>
                  <button type="button" class="btn btn-outline-dark btn-rounded fs-5 fw-bold" data-mdb-ripple-color="dark" data-bs-dismiss="modal">NO, THANKS</button>
                </div>
              </div>
            </div>
          </div>

      <div class="box btn d-flex flex-row justify-content-around flex-wrap ">
        <div @click="findIndex(x), show = !show, showOff = !showOff" v-for="x in client.accounts" :key="x.id" class="col-xl-6 col-lg-6 col-md-6 col-sm-6 mt-4">
            <div class="tarjeta l-bg-green-dark ">
                <div class="tarjeta-statistic-3 p-4">
                    <div class="tarjeta-icon tarjeta-icon-large"><i class="fas fa-dollar-sign"></i></div>
                    <div class="mb-4">
                        <h5 class="tarjeta-title mb-0">N° Account: {{ x.number }}</h5>
                    </div>
                    <div class="mb-2 d-flex justify-content-between">
                        <div class="justify-content-end">
                            <h2 class=" mb-0">
                                ${{x.balance.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}}
                            </h2>
                          </div>
                         <div class="text-right align-content-end text-end">
                            <span class="fw-bold">{{ x.creationDate.slice(0,10) }}</span>
                        </div>
                        </div>
                        <div class="progress mt-1  " data-height="8" style="height: 8px;">
                        <div class="progress-bar l-bg-orange" role="progressbar" data-width="25%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100000" :style="'width:'+x.balance/500+'%;'"></div>
                    </div>
                   </div>
                </div>
              </div>
            </div>

              <!-- toast -->
              <div class="position-absolute top-0 end-0 p-3" style="z-index: 11">
                <div id="liveToast" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true">
                  <div class="toast-header">
                    <img src="https://image.flaticon.com/icons/png/128/190/190411.png" class="rounded me-2" alt="">
                    <strong class="me-auto">Congrats you have a new account!</strong>
                    </div>
                    <div class="toast-body">
                    <p class="text-dark"> Now you can see it in the accounts section</p>
                  </div>
                </div>
              </div>

        </div>
        </transition>


<div v-if="showOff" class="container animate__animated animate__zoomIn">
<div class="card mt-3 ">
  <div class="card-header fw-bold text-white">
    SB $ {{account.number}}
  </div>
  <div class="card mb-3" style="max-width: 740px;">
  <div class="row g-0">
    <div class="col-md-3 text-center mt-5 ps-2">
      Available Balance
      <span class="fw-bold fs-5">$ {{account.balance.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}}</span>
    </div>
    <div class="col-md-9 ">
      <div class="card-body">
        <div class="d-flex flex-row gap-5">
          <div class="d-flex flex-column col-md-5 col-sm-7">
            Holder
            <span class="mb-1 fw-bold">{{client.firstName.toUpperCase()}} {{client.lastName.toUpperCase()}}</span> 
            CUIT/CUIL
            <span class="mb-1 fw-bold">27312227555</span>
          </div>
          <div class="d-flex flex-column col-sm-5">
             Savings bank
            <span class="mb-1 fw-bold">SB $ {{account.number}}</span> 
             Discharge date
            <span class="mb-1 fw-bold">{{account.creationDate}}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>

<div class="d-flex flex-column justify-content-end mt-3">
      <table>
        <thead class="table table-light table-striped">
          <tr>
            <th scope="col">#</th>
            <th scope="col">Type</th>
            <th scope="col">Amount</th>
            <th scope="col">description</th>
            <th scope="col">Date</th>
            <th scope="col">Balance</th>
          </tr>
        </thead>

        <tbody>
          <td v-if="transactions.length == 0" colspan="6">
            <h6 class="text-center">
              You have no transactions made
            </h6>
          </td>
          <tr v-for="x in transactions" :key="x.id">
            <th scope="row">{{ x.id }}</th>
            <td v-if="x.type == 'DEBITO'" class="text-danger fw-bold">{{ x.type }}</td>
            <td v-else class="text-success fw-bold">{{ x.type }}</td>
            <td>$ {{ parseFloat(x.amount).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}}</td>
            <td>{{ x.description }}</td>
            <td>{{ x.date.slice(0,10) }}</td>
            <td class="fw-bold">$ {{parseFloat(x.balanceFinal).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}} </td>
          </tr>
        </tbody>
      </table>
    </div>
</div>


</div>
<!-- fin container  -->
</template>

<script>
import axios from "axios";

export default {

  data() {
    return {
      client: [],
      show:true,
      showOff:false,
      account:[],
      transactions:[],
      accounts:[],
    };
  },
  created() {
    
     axios.post('/api/clients/current/').then(({data}) => { this.client = data; this.accounts = data.accounts })

    //  implements vuex
    //  this.client = this.$store.state.data
    //  this.accounts = this.client.accounts
     
        this.client.accounts.filter(
          (x) => (x.creationDate = x.creationDate.split(/[T,.]/))
        );
        this.client.accounts.filter(
          (x) => (x.creationDate = x.creationDate.splice(0, 2))
        );
        this.client.accounts.filter(
          (x) => (x.creationDate = x.creationDate.join(" "))
        );
  },
  methods: {
    findIndex(x) {
      this.account = x;
      this.transactions = x.transactions;
      this.transactions.sort((a,b) => parseInt(b.id) - a.id)
    },
    addAccount(){
         axios.post('/api/clients/current/accounts', "createNewAccount=true")
         .then(() => {
            $(".modal").modal('hide');
            $(".toast").toast('show');
            this.$emit('setView', 'accounts')           
            })
         .catch(res =>{
            swal({
            title: res.response.data,
            icon: "warning",
            buttons: true,
            })
            console.clear();
            })
    },
    openModal(){
      $(".account").modal('show');
    }
  },
  mounted() {
  },
};
</script>

<style lang="scss" scoped>



@media only screen and (max-width: 414px) {

table th, td{
  font-size: 11px;
}

.childTwo{
  width: 97vw;
}
}

.accordion-item{
 min-width: 50vw;

}

.box{
  
  background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' version='1.1' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns:svgjs='http://svgjs.com/svgjs' width='1440' height='560' preserveAspectRatio='none' viewBox='0 0 1440 560'%3e%3cg mask='url(%26quot%3b%23SvgjsMask3220%26quot%3b)' fill='none'%3e%3crect width='1440' height='560' x='0' y='0' fill='url(%23SvgjsRadialGradient3221)'%3e%3c/rect%3e%3cpath d='M494.015%2c242.808C524.786%2c242.85%2c556.557%2c232.751%2c571.926%2c206.093C587.281%2c179.458%2c581.563%2c146.001%2c564.614%2c120.351C549.334%2c97.225%2c521.716%2c87.208%2c494.015%2c88.181C468.11%2c89.091%2c445.772%2c103.378%2c431.511%2c125.024C415.346%2c149.561%2c402.698%2c179.926%2c416.471%2c205.881C430.855%2c232.986%2c463.33%2c242.766%2c494.015%2c242.808' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float2'%3e%3c/path%3e%3cpath d='M1139.1943905027802 545.3041939608764L1217.8083871568376 549.42417894608 1184.6813663076143 429.443191472392z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float2'%3e%3c/path%3e%3cpath d='M125.256%2c116.025C140.775%2c115.719%2c155.558%2c108.947%2c163.659%2c95.707C172.128%2c81.866%2c173.281%2c64.541%2c165.456%2c50.326C157.356%2c35.611%2c142.043%2c25.364%2c125.256%2c25.946C109.288%2c26.499%2c97.589%2c39.112%2c89.609%2c52.955C81.641%2c66.777%2c76.289%2c83.385%2c84.139%2c97.274C92.083%2c111.329%2c109.114%2c116.343%2c125.256%2c116.025' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float3'%3e%3c/path%3e%3cpath d='M49.11931842867027 324.41578207607506L108.04638066257647 192.0634333205793-24.3059680929193 133.13637108667308-83.2330303268255 265.48871984216885z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M571.5789391878811 112.66391134641351L533.6322882945946 211.51831663026758 670.4333444717352 150.61056223970007z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float3'%3e%3c/path%3e%3cpath d='M-54.094429232004586 311.30062760613714L-15.944061288363649 416.1179020751761 88.87321318067535 377.9675341315352 50.722845237034406 273.1502596624962z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M1394.051%2c578.01C1430.897%2c578.732%2c1469.914%2c570.522%2c1489.723%2c539.446C1510.82%2c506.348%2c1510.881%2c461.941%2c1488.659%2c429.588C1468.618%2c400.41%2c1429.429%2c398.957%2c1394.051%2c400.159C1361.459%2c401.266%2c1327.901%2c408.428%2c1309.958%2c435.659C1290.241%2c465.581%2c1286.432%2c504.968%2c1304.658%2c535.821C1322.62%2c566.227%2c1358.743%2c577.318%2c1394.051%2c578.01' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M481.02 541.73 a128.44 128.44 0 1 0 256.88 0 a128.44 128.44 0 1 0 -256.88 0z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M391.234%2c318.977C423.131%2c321.141%2c455.874%2c305.73%2c470.393%2c277.247C483.975%2c250.602%2c470.749%2c220.118%2c454.391%2c195.081C439.887%2c172.881%2c417.741%2c156.867%2c391.234%2c156.088C363.294%2c155.267%2c334.965%2c166.784%2c321.3%2c191.168C307.882%2c215.112%2c315.548%2c243.556%2c328.724%2c267.635C342.597%2c292.988%2c362.4%2c317.021%2c391.234%2c318.977' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M1077.599%2c689.755C1125.542%2c687.671%2c1160.421%2c649.512%2c1183.917%2c607.67C1206.806%2c566.909%2c1221.281%2c517.292%2c1196.813%2c477.459C1173.123%2c438.893%2c1122.857%2c435.124%2c1077.599%2c434.59C1030.991%2c434.041%2c979.5%2c435.961%2c953.415%2c474.589C924.808%2c516.951%2c928.418%2c573.392%2c953.969%2c617.664C979.527%2c661.947%2c1026.518%2c691.975%2c1077.599%2c689.755' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float3'%3e%3c/path%3e%3cpath d='M291.3282946461394 51.27362180554629L310.1189806222997 169.91334384931153 428.758702666065 151.12265787315118 409.96801668990463 32.48293582938595z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float2'%3e%3c/path%3e%3cpath d='M1119.17%2c451.791C1133.646%2c452.019%2c1150.134%2c451.427%2c1157.638%2c439.046C1165.313%2c426.383%2c1159.82%2c410.442%2c1151.762%2c398.019C1144.497%2c386.819%2c1132.514%2c380.127%2c1119.17%2c379.739C1105.044%2c379.328%2c1090.624%2c384.048%2c1083.12%2c396.023C1075.181%2c408.692%2c1074.626%2c425.312%2c1082.742%2c437.868C1090.306%2c449.569%2c1105.239%2c451.572%2c1119.17%2c451.791' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float2'%3e%3c/path%3e%3cpath d='M1018.032%2c312.031C1046.926%2c311.662%2c1077.392%2c304.68%2c1092.255%2c279.899C1107.461%2c254.545%2c1102.681%2c222.281%2c1086.828%2c197.327C1072.105%2c174.15%2c1045.489%2c163.233%2c1018.032%2c163.06C990.223%2c162.884%2c962.924%2c173.166%2c947.716%2c196.449C930.973%2c222.082%2c924.536%2c255.682%2c940.449%2c281.839C955.908%2c307.249%2c988.291%2c312.411%2c1018.032%2c312.031' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M229.81 115.96 a101.69 101.69 0 1 0 203.38 0 a101.69 101.69 0 1 0 -203.38 0z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float3'%3e%3c/path%3e%3c/g%3e%3cdefs%3e%3cmask id='SvgjsMask3220'%3e%3crect width='1440' height='560' fill='white'%3e%3c/rect%3e%3c/mask%3e%3cradialGradient cx='50%25' cy='50%25' r='772.53' gradientUnits='userSpaceOnUse' id='SvgjsRadialGradient3221'%3e%3cstop stop-color='rgba(249%2c 255%2c 248%2c 1)' offset='0.02'%3e%3c/stop%3e%3cstop stop-color='rgba(255%2c 255%2c 255%2c 1)' offset='0.78'%3e%3c/stop%3e%3cstop stop-color='rgba(255%2c 255%2c 255%2c 1)' offset='0.83'%3e%3c/stop%3e%3cstop stop-color='rgba(248%2c 250%2c 255%2c 1)' offset='1'%3e%3c/stop%3e%3c/radialGradient%3e%3cstyle%3e %40keyframes float1 %7b 0%25%7btransform: translate(0%2c 0)%7d 50%25%7btransform: translate(-10px%2c 0)%7d 100%25%7btransform: translate(0%2c 0)%7d %7d .triangle-float1 %7b animation: float1 5s infinite%3b %7d %40keyframes float2 %7b 0%25%7btransform: translate(0%2c 0)%7d 50%25%7btransform: translate(-5px%2c -5px)%7d 100%25%7btransform: translate(0%2c 0)%7d %7d .triangle-float2 %7b animation: float2 4s infinite%3b %7d %40keyframes float3 %7b 0%25%7btransform: translate(0%2c 0)%7d 50%25%7btransform: translate(0%2c -10px)%7d 100%25%7btransform: translate(0%2c 0)%7d %7d .triangle-float3 %7b animation: float3 6s infinite%3b %7d %3c/style%3e%3c/defs%3e%3c/svg%3e");

}

.container{
 background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' version='1.1' xmlns:xlink='http://www.w3.org/1999/xlink' xmlns:svgjs='http://svgjs.com/svgjs' width='1440' height='560' preserveAspectRatio='none' viewBox='0 0 1440 560'%3e%3cg mask='url(%26quot%3b%23SvgjsMask3220%26quot%3b)' fill='none'%3e%3crect width='1440' height='560' x='0' y='0' fill='url(%23SvgjsRadialGradient3221)'%3e%3c/rect%3e%3cpath d='M494.015%2c242.808C524.786%2c242.85%2c556.557%2c232.751%2c571.926%2c206.093C587.281%2c179.458%2c581.563%2c146.001%2c564.614%2c120.351C549.334%2c97.225%2c521.716%2c87.208%2c494.015%2c88.181C468.11%2c89.091%2c445.772%2c103.378%2c431.511%2c125.024C415.346%2c149.561%2c402.698%2c179.926%2c416.471%2c205.881C430.855%2c232.986%2c463.33%2c242.766%2c494.015%2c242.808' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float2'%3e%3c/path%3e%3cpath d='M1139.1943905027802 545.3041939608764L1217.8083871568376 549.42417894608 1184.6813663076143 429.443191472392z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float2'%3e%3c/path%3e%3cpath d='M125.256%2c116.025C140.775%2c115.719%2c155.558%2c108.947%2c163.659%2c95.707C172.128%2c81.866%2c173.281%2c64.541%2c165.456%2c50.326C157.356%2c35.611%2c142.043%2c25.364%2c125.256%2c25.946C109.288%2c26.499%2c97.589%2c39.112%2c89.609%2c52.955C81.641%2c66.777%2c76.289%2c83.385%2c84.139%2c97.274C92.083%2c111.329%2c109.114%2c116.343%2c125.256%2c116.025' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float3'%3e%3c/path%3e%3cpath d='M49.11931842867027 324.41578207607506L108.04638066257647 192.0634333205793-24.3059680929193 133.13637108667308-83.2330303268255 265.48871984216885z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M571.5789391878811 112.66391134641351L533.6322882945946 211.51831663026758 670.4333444717352 150.61056223970007z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float3'%3e%3c/path%3e%3cpath d='M-54.094429232004586 311.30062760613714L-15.944061288363649 416.1179020751761 88.87321318067535 377.9675341315352 50.722845237034406 273.1502596624962z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M1394.051%2c578.01C1430.897%2c578.732%2c1469.914%2c570.522%2c1489.723%2c539.446C1510.82%2c506.348%2c1510.881%2c461.941%2c1488.659%2c429.588C1468.618%2c400.41%2c1429.429%2c398.957%2c1394.051%2c400.159C1361.459%2c401.266%2c1327.901%2c408.428%2c1309.958%2c435.659C1290.241%2c465.581%2c1286.432%2c504.968%2c1304.658%2c535.821C1322.62%2c566.227%2c1358.743%2c577.318%2c1394.051%2c578.01' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M481.02 541.73 a128.44 128.44 0 1 0 256.88 0 a128.44 128.44 0 1 0 -256.88 0z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M391.234%2c318.977C423.131%2c321.141%2c455.874%2c305.73%2c470.393%2c277.247C483.975%2c250.602%2c470.749%2c220.118%2c454.391%2c195.081C439.887%2c172.881%2c417.741%2c156.867%2c391.234%2c156.088C363.294%2c155.267%2c334.965%2c166.784%2c321.3%2c191.168C307.882%2c215.112%2c315.548%2c243.556%2c328.724%2c267.635C342.597%2c292.988%2c362.4%2c317.021%2c391.234%2c318.977' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M1077.599%2c689.755C1125.542%2c687.671%2c1160.421%2c649.512%2c1183.917%2c607.67C1206.806%2c566.909%2c1221.281%2c517.292%2c1196.813%2c477.459C1173.123%2c438.893%2c1122.857%2c435.124%2c1077.599%2c434.59C1030.991%2c434.041%2c979.5%2c435.961%2c953.415%2c474.589C924.808%2c516.951%2c928.418%2c573.392%2c953.969%2c617.664C979.527%2c661.947%2c1026.518%2c691.975%2c1077.599%2c689.755' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float3'%3e%3c/path%3e%3cpath d='M291.3282946461394 51.27362180554629L310.1189806222997 169.91334384931153 428.758702666065 151.12265787315118 409.96801668990463 32.48293582938595z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float2'%3e%3c/path%3e%3cpath d='M1119.17%2c451.791C1133.646%2c452.019%2c1150.134%2c451.427%2c1157.638%2c439.046C1165.313%2c426.383%2c1159.82%2c410.442%2c1151.762%2c398.019C1144.497%2c386.819%2c1132.514%2c380.127%2c1119.17%2c379.739C1105.044%2c379.328%2c1090.624%2c384.048%2c1083.12%2c396.023C1075.181%2c408.692%2c1074.626%2c425.312%2c1082.742%2c437.868C1090.306%2c449.569%2c1105.239%2c451.572%2c1119.17%2c451.791' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float2'%3e%3c/path%3e%3cpath d='M1018.032%2c312.031C1046.926%2c311.662%2c1077.392%2c304.68%2c1092.255%2c279.899C1107.461%2c254.545%2c1102.681%2c222.281%2c1086.828%2c197.327C1072.105%2c174.15%2c1045.489%2c163.233%2c1018.032%2c163.06C990.223%2c162.884%2c962.924%2c173.166%2c947.716%2c196.449C930.973%2c222.082%2c924.536%2c255.682%2c940.449%2c281.839C955.908%2c307.249%2c988.291%2c312.411%2c1018.032%2c312.031' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float1'%3e%3c/path%3e%3cpath d='M229.81 115.96 a101.69 101.69 0 1 0 203.38 0 a101.69 101.69 0 1 0 -203.38 0z' fill='rgba(28%2c 83%2c 142%2c 0.4)' class='triangle-float3'%3e%3c/path%3e%3c/g%3e%3cdefs%3e%3cmask id='SvgjsMask3220'%3e%3crect width='1440' height='560' fill='white'%3e%3c/rect%3e%3c/mask%3e%3cradialGradient cx='50%25' cy='50%25' r='772.53' gradientUnits='userSpaceOnUse' id='SvgjsRadialGradient3221'%3e%3cstop stop-color='rgba(249%2c 255%2c 248%2c 1)' offset='0.02'%3e%3c/stop%3e%3cstop stop-color='rgba(255%2c 255%2c 255%2c 1)' offset='0.78'%3e%3c/stop%3e%3cstop stop-color='rgba(255%2c 255%2c 255%2c 1)' offset='0.83'%3e%3c/stop%3e%3cstop stop-color='rgba(248%2c 250%2c 255%2c 1)' offset='1'%3e%3c/stop%3e%3c/radialGradient%3e%3cstyle%3e %40keyframes float1 %7b 0%25%7btransform: translate(0%2c 0)%7d 50%25%7btransform: translate(-10px%2c 0)%7d 100%25%7btransform: translate(0%2c 0)%7d %7d .triangle-float1 %7b animation: float1 5s infinite%3b %7d %40keyframes float2 %7b 0%25%7btransform: translate(0%2c 0)%7d 50%25%7btransform: translate(-5px%2c -5px)%7d 100%25%7btransform: translate(0%2c 0)%7d %7d .triangle-float2 %7b animation: float2 4s infinite%3b %7d %40keyframes float3 %7b 0%25%7btransform: translate(0%2c 0)%7d 50%25%7btransform: translate(0%2c -10px)%7d 100%25%7btransform: translate(0%2c 0)%7d %7d .triangle-float3 %7b animation: float3 6s infinite%3b %7d %3c/style%3e%3c/defs%3e%3c/svg%3e");
  
}
.caja{
    margin: 0%;
    padding: 0%;
    min-width: 50vw;
}

.barra{

height: 3rem;
background-image: linear-gradient(to right, #0a504a, #30c569) !important;
box-shadow: rgba(14, 30, 37, 0.12) 0px 2px 4px 0px, rgba(14, 30, 37, 0.32) 0px 2px 16px 0px;
}

.card-header{
    background: linear-gradient(to right, #0a504a, #30c569) !important;;
}

.tarjeta {
    background-color: #fff;
    border-radius: 10px;
    border: none;
    position: relative;
    margin-bottom: 30px;
    box-shadow: 0 0.46875rem 2.1875rem rgba(90,97,105,0.1), 0 0.9375rem 1.40625rem rgba(90,97,105,0.1), 0 0.25rem 0.53125rem rgba(90,97,105,0.12), 0 0.125rem 0.1875rem rgba(90,97,105,0.1);
}

.l-bg-green-dark {
    background: linear-gradient(to right, #0a504a, #38ef7d) !important;
    color: #fff;
}

.tarjeta .tarjeta-statistic-3 .tarjeta-icon-large .fas, .tarjeta .tarjeta-statistic-3 .tarjeta-icon-large .far, .tarjeta .tarjeta-statistic-3 .tarjeta-icon-large .fab, .tarjeta .tarjeta-statistic-3 .tarjeta-icon-large .fal {
    font-size: 110px;
}

.tarjeta .tarjeta-statistic-3 .tarjeta-icon {
    text-align: center;
    line-height: 50px;
    margin-left: 15px;
    color: #000;
    position: absolute;
    right: -5px;
    top: 20px;
    opacity: 0.1;
}

.l-bg-cyan {
    background: linear-gradient(135deg, #289cf5, #84c0ec) !important;
    color: #fff;
}

.l-bg-green {
    background: linear-gradient(135deg, #23bdb8 0%, #43e794 100%) !important;
    color: #fff;
}

.l-bg-orange {
    background: linear-gradient(to right, #f9900e, #ffba56) !important;
    color: #fff;
}

.l-bg-cyan {
    background: linear-gradient(135deg, #289cf5, #84c0ec) !important;
    color: #fff;
}

@media only screen and (max-width: 850px) {
  .card{
   margin-top: 17px!important;
  }
}

/*Efectos transitions de show */

.bounce-leave-active {
  animation: bounce-in .5s reverse;
}
@keyframes bounce-in {
  0% {
    transform: scale(0);
  }
  50% {
    transform: scale(1.5);
  }
  100% {
    transform: scale(1);
  }
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s ease
}

.fade-enter, .fade-leave-to {
  opacity: 0
}

.vue{
  background-color: linear-gradient(to right, #0a504a, #38ef7d) !important;
}

thead{
 background-image: linear-gradient(to right, #0a504a, #38ef7d) !important;
  color: white;
}

.modal {
  .modal-dialog{
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    max-width: 100% !important;
    margin: 0;
    .modal-content{
      background-image: linear-gradient(to left, #0a504a, #38ef7d) !important;
      border-radius: .3rem .3rem 0 0;
    }
  }
}

.btn-close {
  background-image: url('../assets/cross.png');
}

.accordion-item{
    margin-top: 10px;
}

</style>