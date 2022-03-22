package cn.itcast.entity.vo;

import cn.itcast.entity.Algorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlgorithmsVo {
    private  String CatagoryName;
    private List<Algorithm> algorithms;
}
