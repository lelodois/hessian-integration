package br.com.roicamp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.basecamp.message.BaseResult;
import br.com.roicamp.stream.ProcuraVogalStream;

@RestController
@RequestMapping("stream")
public class StreamAPI {

	@GetMapping(value = "/findVogal/{stream}")
	public ResponseEntity<BaseResult<String>> findVogal(@PathVariable(name = "stream", required = true) String stream) {
		String charr = ProcuraVogalStream.findFirstChar(stream);
		if (charr == null) {
			return this.getResponseEntity(new BaseResult<String>(false, "Infelizmente n�o encontramos a vogal..."));
		}
		return this.getResponseEntity(new BaseResult<String>(true, charr));
	}

	private ResponseEntity<BaseResult<String>> getResponseEntity(BaseResult<String> baseResult) {
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<BaseResult<String>>(baseResult, httpHeaders, HttpStatus.OK);
	}

}