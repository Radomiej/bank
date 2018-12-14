package pl.studia.bank

import com.google.gson.Gson
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import pl.studia.bank.config.SwaggerConfig
import pl.studia.bank.controller.BankController
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@SpringBootTest(classes = BankApplication)
class BankAccountsTests extends Specification{

    @Autowired
    BankController bankController



    Gson gson = new Gson()

//    @Before
//    def "init"() {
//        gson = new Gson()
//    }


    def "should add new bank account"(){
        MockMvc mockMvc = standaloneSetup(bankController).build()

        given:
        Map request = [
        balance : 1000.00,
        id : 1,
        ownerId: 1,
        createdAt: '2018-12-11T16:09:43.511Z'
        ]
        when:
        def response = mockMvc.perform(post('/api/v1/controller/addBankAccount')
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andReturn()
                .response

        println "odpowiedz "+response.contentAsString

        then:
        response.contentAsString != null
    }

}