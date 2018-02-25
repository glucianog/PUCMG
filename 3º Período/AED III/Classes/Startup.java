class Startup extends Projeto{
   protected String nome;
   //protected DataURI logo;    
   protected String ideia;

   Startup(){
      super();
      nome = "";
      ideia = "";
      //logo = algumacoisa URI
   }

   Startup(String nome,String ideia, int idProjeto, int idUsuario, byte estado, String descricao){
      setNome(nome);
      //setLogo(logo);        
      setIdeia(ideia);
      super(idProjeto, idUsuario, estado, descricao);
   }      
   
   
   public void setNome(String nome){
      this.nome = nome;
   }

   public void setLogo(DataURI logo){
      this.nome = nome;
   }
 

   public void setIdeia(String ideia){
      this.ideia = ideia;
   }
   
   @Override
   public byte[] getByteArray() throws IOException {
      ByteArrayOutputStream registro = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(registro);
      
      out.write(super.getByteArray());
      out.writeUTF(nome);
      out.writeUTF(ideia);
      //out.write(logo);
      
      return registro.toByteArray();
   }
   
   @Override
   public void setByteArray(byte[] buffer) throws IOException {
      ByteArrayInputStream registro = new ByteArrayInputStream(buffer);
      DataInputSream in = new DataInputStream(registro);
      
      super.setByteArray(buffer,in);
      nome = in.readUTF();
      ideia = in.readUTF();
      //logo = in.readURI();
   }
   
   public Object clone() throws CloneNotSupporteedException {
      return super.clone();
   }   
}
