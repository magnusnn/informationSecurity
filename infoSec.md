#InfoSec

##Web Server
A web server is an information technology that processes requests via HTTP, the basic network protocol used to distribute information on the World Wide Web. The term can refer either to the entire computer system, an appliance, or specifically to the software that accepts and supervises the HTTP requests.

- [web server](https://en.wikipedia.org/wiki/Web_server)

###Apache HTTP Server
>   The Apache HTTP Server is a powerful and flexible HTTP/1.1 compliant
  web server.  Originally designed as a replacement for the NCSA HTTP
  Server, it has grown to be the most popular web server on the
  Internet.  As a project of the Apache Software Foundation, the
  developers aim to collaboratively develop and maintain a robust,
  commercial-grade, standards-based server with freely available
  source code. [github: apache httpd](https://github.com/apache/httpd)

**[SSL/TLS Strong Encryption: How-To](http://httpd.apache.org/docs/2.4/ssl/ssl_howto.html)**

**[Apache Module mod_ssl](http://httpd.apache.org/docs/2.4/mod/mod_ssl.html)**: relies on OpenSSL to provide the cryptography engine.

[PHP 101: PHP For the Absolute Beginner](http://devzone.zend.com/6/php-101-php-    for-the-absolute-beginner/)


###Internet Information Services IIS
> Internet Information Services (IIS, formerly Internet Information Server) is an extensible web server created by Microsoft for use with Windows NT family.

Product|Vendor|January 2016|Percent|February 2016|Percent|Change
---|	---|	---|		---|	---|		---|---
Apache|Apache|304,271,061|33.56%|306,292,557|32.80%|-0.76
IIS|Microsoft|262,471,886|28.95%|278,593,041|29.83%|0.88
nginx|NGINX, Inc.|141,443,630|15.60%|137,459,391|16.61%|-0.88
GWS|Google|20,799,087|2.29%|20,640,058|2.21%|-0.08

## Application Server
An application server is a software framework that provides both facilities to create **web applications** and a **server** environment to run them.

### Java application servers
Java Platform, Enterprise Edition or Java EE defines the core set of API and features of Java Application Servers.

#### The Web modules include:

- Java Servlet
- Java Server Pages **JSP**
	- executes in a web container.
	- JSPs provide a way to create HTML pages by embedding references to the server logic within the page.
- Enterprise JavaBeans
	- are used to manage transactions.

#### Open source Java application servers that support Java EE:

- **GlassFish** : Oracle GlassFish Server
	- is the reference implementation of Java EE
	- supports: 
		- Enterprise JavaBeans
		- JPA: Java Persistence API
		- JavaServer Faces
		- JMS: Java Message Service API
		- RMI
		- JSP
		- Java Servlet
		- ...
	- built on a modular kernel powered by OSGi.
- **TomEE** : (pronounced 'Tommy')
	- Java Enterprise Edition of Apache Tomcat
		- **Tomcat**: 
			- implements several Java EE specifications including:
				- Java Servlet
				- JSP
				- Java EL
				- WebSocket
			- provides pure Java HTTP web server environment in which Java code can run.
	- TomEE = Tomcat + Java EE 
	- combines several Java enterprise projects including:	
		- Apache **Tomcat**:
			- HTTP server and Servlet container supporting Java Servlet and JSP.
		- Apache OpenEJB:
		- Apache OpenWebBeans
		- Apache OpenJPA
		- Apache MyFaces
		- ...
- ...


### .NET Framework
Microsoft positions their middle-tier applications and services infratructure in the Windows Server operating system and the **.NET Framework** technologies in the role of and application server.

#### The Windows Application Server role includes:

- Internet Information Services **IIS**
	- to provide web sever support.
- .NET Framework
	- to provide application support.
- ASP.NET
	- to provide server side scripting
	- ASP : Active Server Pages
- COM+
	- application component communication
	- **COM+** : Microsoft Component Object Model **COM** and Microsoft Transaction Server **MTS**.
	- Component Services
- Message Queuing
	- multithreaded processing
- Windows Communication Foundation **WCF**
	- application communication.

###Certificate authority
> **数字证书认证机构 CA** 也称为电子商务认证中心、电子商务认证授权机构，是负责发放和管理数字证书的权威机构，并作为电子商务交易中受信任的第三方，承担公钥体系中公钥的合法性检验的责任。

> a cerificate authority or certification authority **CA** is an entity that issues digital certificates. A digital certificate certifies the ownership of a public key by the named subject of the certificate. This allows others (relying parties) to rely upon signatures or on assertions made about the private key that corresponds to the certified public key. In this model of trust relationships, a CA is a trusted third party—trusted both by the subject (owner) of the certificate and by the party relying upon the certificate. [wikipedia: CA](https://en.wikipedia.org/wiki/Certificate_authority)


**[How to create and use SSL Certificates](http://www.eclectica.ca/howto/ssl-cert-howto.php)** : This document describes how to establish yourself as a root certificate authority (root CA) using the OpenSSL toolset. As a root CA, you are able to sign and install certificates for use in your Internet server applications, such as Apache and Stunnel.

####OpenSSL

> In computer networking, OpenSSL is a software library to be used in applications that need to secure communications against eavesdropping or need to ascertain the identity of the party at the other end. It has found wide use in internet web servers, serving a majority of all web sites.

> OpenSSL contains an open-source implementation of the SSL and TLS protocols. The core library, written in the C programming language, implements basic cryptographic functions and provides various utility functions. Wrappers allowing the use of the OpenSSL library in a variety of computer languages are available. [wikipedia: OpenSSL](https://en.wikipedia.org/wiki/OpenSSL)

- [https://www.openssl.org/](https://www.openssl.org/): openssl program is a command line tool for using the various cryptography functions of OpenSSL's crypto library from the shell. It can be used for:
	-  o  Creation and management of private keys, public keys and parameters
	-  o  Public key cryptographic operations
	-  o  Creation of X.509 certificates, CSRs and CRLs
	-  o  Calculation of Message Digests
	-  o  Encryption and Decryption with Ciphers
	-  o  SSL/TLS Client and Server Tests
	-  o  Handling of S/MIME signed or encrypted mail
	-  o  Time Stamp requests, generation and verification
- [https://github.com/openssl/openssl](https://github.com/openssl/openssl)

####Pretty Good Privacy
> Pretty Good Privacy (PGP) is a data encryption and decryption computer program that provides cryptographic privacy and authentication for data communication. PGP is often used for signing, encrypting, and decrypting texts, e-mails, files, directories, and whole disk partitions and to increase the security of e-mail communications.[wikipedia: PGP](https://en.wikipedia.org/wiki/Pretty_Good_Privacy)

####GNU Privacy Guard
> GNU Privacy Guard (GnuPG or GPG) is a free software replacement for PGP.
> GnuPG is a hybrid-encryption software program because it uses a combination of conventional symmetric-key cryptography for speed, and public-key cryptography for ease of secure key exchange, typically by using the recipient's public key to encrypt a session key which is only used once. This mode of operation is part of the OpenPGP standard and has been part of PGP from its first version.



##Reference
- **[Creating and Using SSL Certificates](http://www.eclectica.ca/howto/ssl-cert-howto.php)**
- [wikipedia]
