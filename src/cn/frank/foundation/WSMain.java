package cn.frank.foundation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService(name="hello",serviceName="helloService",portName="hp")
public class WSMain {

	@WebMethod
	public String sayHello(@WebParam(name="hello") String str){
		return "Hello: "+str;
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9090/hello", new WSMain());
	}
}
/*
 *
 http://localhost:9090/hello?wsdl
<definitions targetNamespace="http://foundation.frank.cn/" name="helloService">
	<types>
		<xsd:schema>
		  <xsd:import namespace="http://foundation.frank.cn/" schemaLocation="http://localhost:9090/hello?xsd=1"/>
		</xsd:schema>
	</types>
	<message name="sayHello">
	    <part name="parameters" element="tns:sayHello"/>
	</message>
	<message name="sayHelloResponse">
	    <part name="parameters" element="tns:sayHelloResponse"/>
	</message>
	<portType name="hello">
		<operation name="sayHello">
		    <input wsam:Action="http://foundation.frank.cn/hello/sayHelloRequest" message="tns:sayHello"/>
		    <output wsam:Action="http://foundation.frank.cn/hello/sayHelloResponse" message="tns:sayHelloResponse"/>
		</operation>
	</portType>
	<binding name="hpBinding" type="tns:hello">
	<soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
		<operation name="sayHello">
		<soap:operation soapAction=""/>
		<input>
		      <soap:body use="literal"/>
		</input>
		<output>
		       <soap:body use="literal"/>
		</output>
		</operation>
	</binding>
	<service name="helloService">
		<port name="hp" binding="tns:hpBinding">
			<soap:address location="http://localhost:9090/hello"/>
		</port>
	</service>
</definitions>
*/