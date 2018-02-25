class Pesquisa extends Pesquisa{

   protected boolean andamento;
   protected String area;
   protected char turno;
   
   
   public Pesquisa(){
      super();
      andamento = false;
      area = "";
      turno = ' ';
   }
   
   public Pesquisa(boolean andamento, String area, char turno, int idProjeto, int idUsuario, byte estado, String descricao){
      setAndamento(andamento);
      setArea(area);
      setTurno(turno);
      super(idProjeto, idUsuario, estado, descricao);
   }
   
   public void setAndamento(boolean andamento){
      this.andamento = andamento;
   }
   
   public void setArea(String area){
      this.area = area;
   }
   
   public void setTurno(char turno){
      this.turno = turno;
   }
   
   @Override
   public byte[] getByteArray() throws IOException {
      ByteArrayOutputStream registro = new ByteArrayOutputStream();
      DataOutputStream out = new DataOutputStream(registro);
      
      out.write(super.getByteArray());
      out.writeBoolean(andamento);
      out.writeUTF(area);
      out.writeChar((int)turno);     
      
      return registro.toByteArray();
   }
   
   @Override
   public void setByteArray(byte[] buffer) throws IOException {
      ByteArrayInputStream registro = new ByteArrayInputStream(buffer);
      DataInputSream in = new DataInputStream(registro);
      
      super.setByteArray(buffer,in);
      andamento = in.readBoolean();
      area = in.readUTF();
      turno = in.readChar();
   }
            
   public Object clone() throws CloneNotSupporteedException {
      return super.clone();
   }      
}
