package com.tansha.library.bookshelf.admin.login.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tansha.library.bookshelf.admin.login.configurations.MySimpleUrlAuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Autowired
    private UserDetailsService userDetailsService;

  /*  @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
    	
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                //.authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }*/
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/cart").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/checkemail/**").permitAll()
                .antMatchers("/forgotpassword").permitAll()
                .antMatchers("/resetpassword").permitAll()
                .antMatchers("/reset").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/initialRegistration/**").permitAll()
                .antMatchers("/managearea").permitAll()
                .antMatchers("/areas").permitAll()
                .antMatchers("/addarea").permitAll()
                .antMatchers("/updatearea").permitAll()
                .antMatchers("/updatearea/**").permitAll()
                .antMatchers("/deletearea/**").permitAll()
                .antMatchers("/manageauthor").permitAll()
                .antMatchers("/authors").permitAll()
                .antMatchers("/addauthor").permitAll()
                .antMatchers("/updateauthor").permitAll()
                .antMatchers("/updateauthor/**").permitAll()
                .antMatchers("/deleteauthor/**").permitAll()
                .antMatchers("/managebookcategory").permitAll()
                .antMatchers("/bookcategories").permitAll()
                .antMatchers("/addbookcategory").permitAll()
                .antMatchers("/updatebookcategory").permitAll()
                .antMatchers("/updatebookcategory/**").permitAll()
                .antMatchers("/deletebookcategory/**").permitAll()
                .antMatchers("/managepublisher").permitAll()
                .antMatchers("/publishers").permitAll()
                .antMatchers("/addpublisher").permitAll()
                .antMatchers("/updatepublisher").permitAll()
                .antMatchers("/updatepublisher/**").permitAll()
                .antMatchers("/deletepublisher/**").permitAll()
                .antMatchers("/managelanguage").permitAll()
                .antMatchers("/languages").permitAll()
                .antMatchers("/addlanguage").permitAll()
                .antMatchers("/updatelanguage").permitAll()
                .antMatchers("/updatelanguage/**").permitAll()
                .antMatchers("/deletelanguage/**").permitAll()
                .antMatchers("/managereadinglevel").permitAll()
                .antMatchers("/readinglevels").permitAll()
                .antMatchers("/addreadinglevel").permitAll()
                .antMatchers("/updatereadinglevel").permitAll()
                .antMatchers("/updatereadinglevel/**").permitAll()
                .antMatchers("/deletereadinglevel/**").permitAll()
                .antMatchers("/managestaff").permitAll()
                .antMatchers("/staffs").permitAll()
                .antMatchers("/addstaff").permitAll()
                .antMatchers("/updatestaff").permitAll()
                .antMatchers("/updatestaff/**").permitAll()
                .antMatchers("/deletestaff/**").permitAll()
                .antMatchers("/managebook").permitAll()
                .antMatchers("/books").permitAll()
                .antMatchers("/addbook").permitAll()
                .antMatchers("/updatebook").permitAll()
                .antMatchers("/updatebook/**").permitAll()
                .antMatchers("/deletebook/**").permitAll()
                .antMatchers("/managedeliveryslot").permitAll()
                .antMatchers("/deliveryslots").permitAll()
                .antMatchers("/adddeliveryslot").permitAll()
                .antMatchers("/updatedeliveryslot").permitAll()
                .antMatchers("/updatedeliveryslot/**").permitAll()
                .antMatchers("/deletedeliveryslot/**").permitAll()
                .antMatchers("/managesubscriptionrule").permitAll()
                .antMatchers("/subscriptionrules").permitAll()
                .antMatchers("/addsubscriptionrule").permitAll()
                .antMatchers("/updatesubscriptionrule").permitAll()
                .antMatchers("/updatesubscriptionrule/**").permitAll()
                .antMatchers("/deletesubscriptionrule/**").permitAll()
                .antMatchers("/managesubscription").permitAll()
                .antMatchers("/subscriptions").permitAll()
                .antMatchers("/addsubscription").permitAll()
                .antMatchers("/updatesubscription").permitAll()
                .antMatchers("/updatesubscription/**").permitAll()
                .antMatchers("/deletesubscription/**").permitAll()
                .antMatchers("/managestock").permitAll()
                .antMatchers("/reportstafftoday").permitAll()
                .antMatchers("/reporttodaydelivery").permitAll()
                .antMatchers("/reporttodaypickup").permitAll()
                .antMatchers("/reportnextdaydelivery").permitAll()
                .antMatchers("/reportnextdaypickup").permitAll()
                .antMatchers("/datewisereport").permitAll()
                .antMatchers("/datewisereport").permitAll()
                
                .antMatchers("/*Wrap*").permitAll()
                .antMatchers("/bookdetails/**").permitAll()
                .antMatchers("/selectSearch/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ROLE","ROLE1").anyRequest()
                //.antMatchers("/user/**").hasAuthority("ROLE_USER").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                //.successHandler(myAuthenticationSuccessHandler())
                .defaultSuccessUrl("/myupcomingdeliveries")
                //.usernameParameter("staffName")
                .usernameParameter("email")
                .passwordParameter("password")
                
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }
    
    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
    }
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**","/datatables/**","/slimScroll/**","/jQueryUI/**","/jQuery/**","/images/**","/fastclick/**","/datatables/**","/bootstrap/**" , "/fonts/**", "/static/**", "/css/**", "/js/**","/webfonts/**","/bookimages/**", "/images/**","/static/css/admin/**/","/static/css/admin/**","/static/css/admin/fonts/**","/static/css/admin/fonts/**/");
    }

}
