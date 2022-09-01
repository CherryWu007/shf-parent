var util = {
    getQueryVariable(variable) {
        //window.location  http://localhost:8001/info.html?id=2&name=zhangsan
        //window.location.search   ?id=2&name=zhangsan
        //window.location.search.substring(1); //id=2&name=zhangsan
        var query = window.location.search.substring(1);
        //[id=2 ,name=zhangsan]
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            //[id,2] [name,zhangsan]
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    },

    getOriginUrl() {
        //login.html?originUrl=http://localhost:8001/info.html?id=1
        //originUrl=http://localhost:8001/info.html?id=1
        var query = window.location.search.substring(1);
        if(query.indexOf("originUrl") != -1) {
            var vars = query.split("originUrl=");
            return vars[1];
        }
        return ""
    }
}