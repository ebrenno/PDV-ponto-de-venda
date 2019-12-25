package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DescontoDAO {

    public static int getPercentualSobreQuantidade(Connection conexao, int codProd, int quantidade) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        ps = conexao.prepareStatement("select percentual from desconto where codprod = ? and ? between qtd_min and qtd_max;");
        ps.setInt(1, codProd);
        ps.setInt(2, quantidade);

        rs = ps.executeQuery();

        if (rs.next()) {
            int percentual = rs.getInt("percentual");
            return percentual;
        } else {

            return 0;
        }
    }

}
