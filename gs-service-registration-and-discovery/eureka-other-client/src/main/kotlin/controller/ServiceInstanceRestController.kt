package controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class ServiceInstanceRestController {

    @Autowired
    lateinit var discoveryClient: DiscoveryClient

    @RequestMapping("/service-instances/{applicationName}")
    fun serviceInstancesByApplicationName(@PathVariable applicationName: String) = discoveryClient.getInstances(applicationName)

}