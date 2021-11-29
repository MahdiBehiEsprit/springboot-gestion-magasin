package tn.esprit.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import tn.esprit.demo.entities.CategorieClient;
import tn.esprit.demo.entities.Client;
import tn.esprit.demo.entities.Profession;
import tn.esprit.demo.service.ClientService;

	@RestController
	@Api(tags="Client management")
	@RequestMapping("/client")
	class ClientRestController {

	@Autowired
	ClientService clientService;

	//http://localhost:8089/SpringMVC/client/retrieve-all-clients
	@GetMapping("retrieve-all-clients")
	@ApiOperation(value="Récupérer la liste des clients")
	@ResponseBody
	public List<Client> getClients() throws ParseException{

		List<Client> listClients= clientService.retrieveAllClients();
		return listClients;
	}

	//http://localhost:8089/SpringMVC/client/remove-client/1
	@DeleteMapping("remove-client/{client-id}")
	@ApiOperation(value="Remove client by id")
	@ResponseBody
	public void removeClientById(@PathVariable("client-id") Long clientId) {
		clientService.deleteClientById(clientId);
	}

	//http://localhost:8089/SpringMVC/client/modify-client
	@PutMapping("/modify-client")
	@ApiOperation(value="Modify client")
	@ResponseBody
	public Client modifyClient(@RequestBody Client client){
		return clientService.updateClient(client);
	}

	//http://localhost:8089/SpringMVC/client/add-client
	@PostMapping("/add-client")
	@ApiOperation(value="Add client")
	@ResponseBody
	public Client addClient(@RequestBody Client c)
	{
		//TODO fix client all null
		Client client = clientService.addClient(c);
		return client;
	}
	
	@GetMapping("retrieve-client/{client-id}")
	@ApiOperation(value="Retrieve client by id")
	@ResponseBody
	public Client retrieveClientById(@PathVariable("client-id") Long clientId)
	{
		return clientService.retrieveClientById(clientId);
	}
	
	@PostMapping("/getChiffreAffaire/{client-categorie}/{startDate}/{endDate}")//cuz we are not viewing sth that can't be changed like in Get
	@ApiOperation(value="getChiffreAffaireParCategorieClient")
	@ResponseBody
	public float getChiffreAffaireParCategorieClient(@RequestBody CategorieClient categorieclient,
													 @PathVariable(name="startDate")
													 @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date startDate,
													 @PathVariable(name="endDate")
														 @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)Date endDate)
	{
		return clientService.getChiffreAffaireParCategorieClient(categorieclient, startDate, endDate);
	}
}