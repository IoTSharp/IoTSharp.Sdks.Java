package iotsharp.models.result;


import iotsharp.models.result.entities.Customer;
import iotsharp.models.result.entities.Tenant;

public class MyInfoResult {


        private String appName;
        private Customer customer;
        private String email;
        private String[] modules;

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String[] getModules() {
            return modules;
        }

        public void setModules(String[] modules) {
            this.modules = modules;
        }


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    private Tenant tenant;
        private String username;




}
