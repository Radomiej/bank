package pl.studia.bank

import com.google.gson.Gson
import org.junit.Before
import org.spockframework.compiler.model.SetupBlock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import pl.studia.bank.controller.BankController
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup


@SpringBootTest(classes = BankApplication)
class BankAccountsTests extends Specification{

    @Autowired
    BankController bankController

    Gson gson = new Gson()



    def "should add new bank account"(){
        MockMvc mockMvc = standaloneSetup(bankController).build()
        given: 'example form parameters'
        Map request = [
        balance : 1000.00,
        id : 1,
        ownerId: 1,
        createdAt: '2018-12-11T16:09:43.511Z'
        ]
        when: 'post on endpoint to add bank account'
        def response = mockMvc.perform(post('/api/v1/controller/addBankAccount')
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(request)))
                .andReturn()
                .response

        println "odpowiedz "+response.contentAsString

        then: 'should return status created'
        response.status == HttpStatus.OK.value() // zamiast status OK zrobić CREATED (201)

        and:
        with(gson.fromJson(response.contentAsString,Map)){
            it.success == true
        }

    }

}