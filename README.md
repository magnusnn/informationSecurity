# Information Security

> Information security, sometimes shortened to InfoSec, is the practice of defending information from unauthorized access, use, disclosure, disruption, modification, perusal, inspection, recording or destruction.
> [wikipedia: InfoSec](https://en.wikipedia.org/wiki/Information_security)

### Authentication

- 身份验证
- Verifying that the user is who she claims to be.

> is the act of confirming the truth of an attribute of a single piece of data (a datum) claimed true by an entity. In contrast with identification which refers to the act of stating or otherwise indicating a claim purportedly attesting to a person or thing's identity, authentication is the process of actually confirming that identity. It might involve confirming the identity of a person by validating their identity documents, verifying the authenticity of a website with a digital certificate, determining the age of an artifact by carbon dating, or ensuring that a product is what its packaging and labeling claim to be. In other words, authentication often involves verifying the validity of at least one form of identification.

> - [wikipedia](https://en.wikipedia.org/wiki/Authentication)


### Authorization (or Access Control)

- 授权
- Granting access to specific resources based on an authenticated user's entitlements.

> or authorisation is the function of specifying access rights to resources related to information security and computer security in general and to access control in particular. More formally, "to authorize" is to define an access policy. For example, human resources staff is normally authorized to access employee records and this policy is usually formalized as access control rules in a computer system. During operation, the system uses the access control rules to decide whether access requests from (authenticated) consumers shall be approved (granted) or disapproved (rejected). Resources include individual files or an item's data, computer programs, computer devices and functionality provided by computer applications. Examples of consumers are computer users, computer programs and other devices on the computer.

> [wikipedia](https://en.wikipedia.org/wiki/Authorization)

### Confidentiality, privacy

- 保密
- Keeping information secret. Accesses a message, for example a Web service request or an email, as well as the identity of the sending and receiving parties in a confidential manner. Confidentiality and privacy can be achieved by encrypting the content of a message and obfuscating the sending and receiving parties' identities.

### Integrity ( and non repudiation)

- 完整
- Making sure that a message remains unaltered during transit by having the sender digitally sign the message. A digital signature is used to validate the signature and provides non-repudiation. The timestamp in the signature prevents anyone from replaying this message after the expiration.

#### Non-repudiation

- 不可抵赖

> non-repudiation implies one's intention to fulfill their obligations to a contract. It also implies that one party of a transaction cannot deny having received a transaction nor can the other party deny having sent a transaction.
> [wikipedia](https://en.wikipedia.org/wiki/Information_security#Non-repudiation)

### Credential

- 凭证
- A credential is an attestation of qualification, competence, or authority issued to an individual by a third party with a relevant or de facto authority or assumed competence to do so.
    - Examples of credentials include academic diplomas, academic degrees, certifications, security clearances, identification documents, badges, passwords, user names, keys, powers of attorney, and so on.
- **Digital credentials** are the digital equivalent of paper-based credentials. Just as a paper-based credential could be a passport, a Driver's license, a membership certificate or some kind of ticket to obtain some service, such as a cinema ticket or a public transport ticket, a digital credential is a proof of qualification, competence, or clearance that is attached to a person. Also, digital credentials prove something about their owner. Both types of credentials may contain personal information such as the person's name, birthplace, birthdate, and/or biometric information such as a picture or a finger print.  

## Transport-level and Application-level Security

Security concepts can be divided into Transport and Application level Security.

### Transport-level Security
Transport-level security secures the communications channel between applications.

![transport security](doc/img/transportSecurity.png)

> An example of a transport-level security protocol is **Secure Socket Layer (SSL)**, otherwise known as **Transport Layer Security (TLS)**

transport-level data-communication protocol providing:

- Authentication (the communication is established between two trusted parties).
-  Confidentiality (the data exchanged is encrypted).
-  Message integrity (the data is checked for possible corruption).
-  Secure key exchange between client and server.
  
> SSL provides a secure communication channel, however, when the data is not "in transit," the data is not protected. This makes the environment vulnerable to attacks in multi-step transactions. (SSL provides point-to-point security, as opposed to end-to-end security.)

> SSL uses a combination of secret-key and public-key cryptography to secure communications. SSL traffic uses secret keys for encryption and decryption, and the exchange of public keys is used for mutual authentication of the parties involved in the communication.

### Application-level Security

Application-level security complements transport-level security. Application-level security is based on XML frameworks defining message confidentiality, integrity, authenticity (also known as message protection); message structure; trust management and federation.



## [OAuth](OAuth.md)

# reference 

- [docs.oracle: Understanding Web Service Security Concepts](https://docs.oracle.com/middleware/1212/owsm/OWSMC/owsm-security-concepts.htm#OWSMC1508)
- [microsoft: Transport level security](https://msdn.microsoft.com/library/hh323709(v=vs.100).aspx)
- [wikipedia: Information security](https://en.wikipedia.org/wiki/Information_security)
- [wikipedia: Transport Layer security](https://en.wikipedia.org/wiki/Transport_Layer_Security)

