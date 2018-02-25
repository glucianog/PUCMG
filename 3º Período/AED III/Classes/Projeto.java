abstract class Projeto implements Registro{
   protected int idProjeto;
   protected int idUsuario;
   protected byte estado;
   protected String descricao;
   
   public Projeto(){
      idProjeto = 0;
      idUsuario = 0;
      estado = 0;
      descricao = "";
   }
   
   public Projeto(int idProjeto,int idUsuario, byte estado, String descricao){
      this.idProjeto = idProjeto;
      this.idUsuario = idUsuario;
      this.esatdo = estado;
      this.descricao = descricao;
      
   }     

   public void setCodigoProjeto(int codigo){
      this.idProjeto = codigo;
   }

   public int getCodigoProjeto(){
      return idProjeto;
   }

   public String getString() {
      return descricao;
   }
   
   public  byte[] getByteArray() throws IOException {
      ByteArrayOutputStream registro = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(registro);
      
      
      out.writeInt(idProjeto);      
      out.writeInt(idUsuario);
      out.writeByte(estado);
      out.writeUTF(descricao);
      
      return registro.toByteArray();    
    
   }
   public void setByteArray(byte[] buffer, DataInputStream in) throws IOException{
      idProjeto = in.readInt();
      idUsuario = in.readInt();
      estado = in.readByte();
      descricao = in.readUTF();     
   }

   
   public Object clone() throws CloneNotSupportedException {
      return super.clone();
   }
}
